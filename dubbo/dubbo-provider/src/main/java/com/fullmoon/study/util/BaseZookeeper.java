package com.fullmoon.study.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Log4j2
public class BaseZookeeper implements Watcher {
	public static ZooKeeper zooKeeper;

	private static final int SESSION_TIME_OUT = 60000;
	private static final String ZOOKEEPER_CLOSED_STATE = "CLOSED";
	private CountDownLatch countDownLatch;

	/**
	 * 连接zookeeper
	 * 
	 */
	public void connectZookeeper() {
		try {
			if (zooKeeper == null || zooKeeper.getState().toString().equals(ZOOKEEPER_CLOSED_STATE)) {
				// System.setProperty("jute.maxbuffer",PropertyUtil.getProperty("jute.maxbuffer"));
				String host = PropertyUtil.getProperty("zookeeper.address");
				zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
				countDownLatch = new CountDownLatch(1);
				countDownLatch.await();
				log.info("connect zookeeper....");
			}
		} catch (Exception e) {
			log.error("fail to connect zookeeper, cause:" + e.getMessage(), e);
		}
	}

	/**
	 * 实现watcher的接口方法，当连接zookeeper成功后，zookeeper会通过此方法通知watcher<br>
	 * 此处为如果接受到连接成功的event，则countDown，让当前线程继续其他事情。
	 * 
	 * @param event
	 *            事件
	 */
	@Override
	public void process(WatchedEvent event) {
		if (event.getState() == KeeperState.SyncConnected) {
			countDownLatch.countDown();
		}
	}

	/**
	 * 根据路径创建节点，并且设置节点数据
	 * 
	 * @param path
	 *            编码后的路径
	 * @param data
	 *            内容
	 * @return boolean 是否创建成功
	 * @throws Exception
	 *             抛出异常
	 */
	public synchronized static Boolean createNode(String path, String data) throws Exception {
		try {
			if (StringUtils.isEmpty(path)) {
				log.error("fail to create node, cause the path is empty");
				return false;
			}
			String[] paths = path.split("/");
			String tempPath = "";
			for (int i = 1; i < paths.length; i++) {
				tempPath += "/" + paths[i];
				Stat stat = zooKeeper.exists(tempPath, false);
				if (stat == null) {
					if (i == paths.length - 1) {
						log.info("success to create :" + tempPath + ", data :" + data);
						zooKeeper.create(tempPath, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
					} else {
						log.info("success to create :" + tempPath);
						zooKeeper.create(tempPath, new byte[] {}, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
					}
				} else if (i == paths.length - 1) {
					log.info("success to update :" + tempPath + ", data :" + data);
					setData(tempPath, data);
				}
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception(e);
		}
	}

	/**
	 * 根据路径设置节点数据
	 * 
	 * @param path
	 *            路径
	 * @param data
	 *            内容
	 * @return Stat 是否设置成功
	 * @throws Exception
	 *             抛出异常
	 */
	public synchronized static Stat setData(String path, String data) throws Exception {
		try {
			Stat stat = zooKeeper.exists(path, false);
			if (stat == null) {
				log.warn("the path not exist,path :" + path);
				return null;
			}
			if (StringUtils.isEmpty(data)) {
				return zooKeeper.setData(path, new byte[] {}, -1);
			} else {
				return zooKeeper.setData(path, data.getBytes(), -1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception(e);
		}
	}

	/**
	 * 根据路径获取所有孩子节点
	 * 
	 * @param path
	 *            路径
	 * @return List 子节点
	 */
	public static List<String> getChildren(String path) {
		try {
			Stat stat = zooKeeper.exists(path, false);
			if (stat == null) {
				log.warn("the path not exist,path :" + path);
				return null;
			}
			List<String> result = zooKeeper.getChildren(path, false);
			if (!CollectionUtils.isEmpty(result)) {
				return result;
			}
		} catch (Exception e) {
			log.error("获取ZK子节点失败,节点路径为：" + path);
		}
		return new ArrayList<String>();
	}

	/**
	 * 根据路径获取节点数据
	 * 
	 * @param path
	 *            节点
	 * @return 节点内容
	 */
	public static String getData(String path) {
		try {
			Stat stat = zooKeeper.exists(path, false);
			if (stat == null) {
				log.warn("the path not exist,path :" + path);
				return null;
			}
			byte[] datas = zooKeeper.getData(path, false, null);
			if (datas == null) {
				log.info("获取节点数据成功, path={},data={}", path, "");
				return "";
			}
			log.info("获取节点数据成功, path={},data={}", path, new String(datas));
			return new String(datas);
		} catch (Exception e) {
			log.info("获取节点数据失败, path={},data={}", path, "");
			log.error(e.getMessage() + e);
		}
		return null;
	}

	/**
	 * 删除节点
	 * 
	 * @param path
	 *            节点路径
	 * @return 是否删除成功
	 * @throws Exception
	 *             抛出的异常
	 */
	public synchronized static Boolean deleteNode(String path) throws Exception {
		if (StringUtils.isEmpty(path)) {
			log.warn("fail to delete node,cause path is empty");
			return false;
		}
		Stat stat = zooKeeper.exists(path, false);
		if (stat == null) {
			log.warn("the path not exist,path :" + path);
			return false;
		}
		zooKeeper.delete(path, -1);
		return true;
	}

	/**
	 * 关闭zookeeper连接
	 * 
	 * @throws Exception
	 *             异常
	 */
	public static void closeConnect() throws Exception {
		if (null != zooKeeper) {
			zooKeeper.close();
		}
	}
}