package com.fullmoon.study.util;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Redis集群工具类
 *
 */
@Log4j2
public class RedisUtils{

    // jedis集群对象
    private static JedisCluster jedisCluster = null;
    /**
     * 路径，格式为：192.168.16.16,192.168.16.17
     */
    private static String[] paths = PropertyUtil.getProperty("redis.path").split(",");
    /**
     * 端口，格式为：7000,7001
     */
    private static String[] ports = PropertyUtil.getProperty("redis.port").split(",");
    /**
     * 过期时间(ms)
     */
    private static long expireTimeMillis = 86400000;
    /**
     * 线程池最大数
     */
    private static int poolMaxTotal = 50;
    /**
     * 线程池最大等待时间(ms)
     */
    private static int poolMaxWaitMillis = 30000;
    /**
     * 超时时间(ms)
     */
    private static int timeout = 500;
    /**
     * REDIS节点信息
     */
    private static Set<HostAndPort> hosts = new HashSet<>();

    /**
     * 无参构造
     */
    public RedisUtils() {
        super();
    }

    /**
     * 参数 paths
     *
     * @return paths
     */
    public static String[] getPaths() {
        return paths;
    }

    /**
     * 参数 paths
     *
     * @param paths
     *            the paths to set
     */
    public static void setPaths(String[] paths) {
        RedisUtils.paths = paths;
    }

    /**
     * 参数 ports
     *
     * @return ports
     */
    public static String[] getPorts() {
        return ports;
    }

    /**
     * 参数 ports
     *
     * @param ports
     *            the ports to set
     */
    public static void setPorts(String[] ports) {
        RedisUtils.ports = ports;
    }

    /**
     * 参数 expireTimeMillis
     *
     * @return expireTimeMillis
     */
    public static long getExpireTimeMillis() {
        return expireTimeMillis;
    }

    /**
     * 参数 expireTimeMillis
     *
     * @param expireTimeMillis
     *            the expireTimeMillis to set
     */
    public static void setExpireTimeMillis(long expireTimeMillis) {
        RedisUtils.expireTimeMillis = expireTimeMillis;
    }

    /**
     * 参数 poolMaxTotal
     *
     * @return poolMaxTotal
     */
    public static int getPoolMaxTotal() {
        return poolMaxTotal;
    }

    /**
     * 参数 poolMaxTotal
     *
     * @param poolMaxTotal
     *            the poolMaxTotal to set
     */
    public static void setPoolMaxTotal(int poolMaxTotal) {
        RedisUtils.poolMaxTotal = poolMaxTotal;
    }

    /**
     * 参数 poolMaxWaitMillis
     *
     * @return poolMaxWaitMillis
     */
    public static int getPoolMaxWaitMillis() {
        return poolMaxWaitMillis;
    }

    /**
     * 参数 poolMaxWaitMillis
     *
     * @param poolMaxWaitMillis
     *            the poolMaxWaitMillis to set
     */
    public static void setPoolMaxWaitMillis(int poolMaxWaitMillis) {
        RedisUtils.poolMaxWaitMillis = poolMaxWaitMillis;
    }

    /**
     * 在spring容器初始化的时候初始化redis连接
     */
    //@PostConstruct
    public static void init() {
        if (!isConnected()) {
            jedisCluster = redisClusterConn();
            if (isConnected()) {
                log.warn("redis connected【{}】", getAddress());
            } else {
                log.warn("JedisCluster init failed");
            }
        }
    }

    /**
     * 参数 jedisCluster
     *
     * @return jedisCluster
     * @throws Exception
     *             异常
     */
    public static JedisCluster getJedisCluster() {
        if (jedisCluster == null) {
            jedisCluster = redisClusterConn();
        }
        return jedisCluster;
    }

    /**
     * 连接redis
     *
     * @return JedisCluster
     */
    private static JedisCluster redisClusterConn() {
        try {
            /*
             * 设置连接池配置
             */
            GenericObjectPoolConfig config = new GenericObjectPoolConfig();
            // 线程池最大连接数
            config.setMaxTotal(getPoolMaxTotal());
            // 线程池最大等待时间
            config.setMaxWaitMillis(getPoolMaxWaitMillis());
            // 获取连接时检查有效性
            config.setTestOnBorrow(true);
            // 在获取返回结果时检查有效性,默认false
            config.setTestOnReturn(true);
            String[] paths = getPaths();
            String[] ports = getPorts();
            // path or port is null
            if (paths == null || ports == null || paths.length != ports.length) {
                log.error("redis paths or ports is null,paths is {} and ports is {}", paths, ports);
                return null;
            }
            if (hosts.isEmpty()) {
                for (int i = 0; i < paths.length; i++) {
                    hosts.add(new HostAndPort(paths[i], Integer.parseInt(ports[i])));
                }
            }
            /*
             * cluster connect to REDIS,
             * timwout:connectionTimeout(连接超时时间),soTimeout(读取数据超时时间)
             */
            return new JedisCluster(hosts, timeout, config);
        } catch (Exception e) {
            // 连接异常
            log.error("fail to connection redis cluster, cause:", e);
        }
        return null;
    }

    /**
     * 获取redis节点地址
     *
     * @return redis节点地址
     */
    public static Set<HostAndPort> getAddress() {
        return hosts;
    }

    /**
     * redis连接状态
     *
     * @return redis连接状态
     */
    public static boolean isConnected() {
        if (jedisCluster != null) {
            try {
                jedisCluster.exists("key");
                return true;
            } catch (Exception e) {
                log.warn("redis connected fail");
            }
        }
        return false;
    }

    /**
     * 释放jedisCluster连接
     */
    public void release() {
        if (jedisCluster != null) {
            try {
                // 关闭jedisCluster连接
                jedisCluster.close();
            } catch (Exception e) {
                log.error("redis connection close error");
                log.error(e.getMessage(), e);
            }
        }
        jedisCluster = null;
    }

    /**
     * 判断键是否存在
     *
     * @param key
     *            键名
     * @return 是否存在
     */
    public boolean exists(String key){
        try {
            return jedisCluster.exists(key);
        } catch (Exception e) {
            log.error("redisKeyExist[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 设置key-value
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param milliseconds
     *            生存时间(ms)
     */
    public boolean set(String key, String value, long milliseconds){
        try {
            if (milliseconds <= 0) {
                // 不设置 生存时间
                return "OK".equals(jedisCluster.set(key, value));
            } else {
                // 设置 生存时间
                return "OK".equals(jedisCluster.psetex(key, milliseconds, value));
            }
        } catch (Exception e) {
            log.error("redis to set key[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 获取键对应的值
     *
     * @param key
     *            键名
     * @return 值
     */
    public String get(String key){
        try {
            return jedisCluster.get(key);
        } catch (Exception e) {
            log.error("redis to get key[{}]:{}", key, e.getMessage());
            return null;
        }
    }

    /**
     * 删除某个键
     * @param key 键名
     * @return 是否删除成功
     */
    public boolean delete(String key){
        try {
            return 1L == jedisCluster.del(key);
        } catch (Exception e) {
            log.error("redis to get key[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 对某个键设置有效时间
     * @param key 键名
     * @param milliseconds 有效时间(ms)
     * @return 是否设置成功
     */
    public boolean pexpire(String key, long milliseconds){
        try {
            return 1L == jedisCluster.pexpire(key, milliseconds);
        } catch (Exception e) {
            log.error("redis to expire key[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 获取key的生存时间(ms) 当 key不存在时,返回 -2; 当 key存在但没有设置剩余生存时间时,返回 -1; 否则,返回
     * key的剩余生存时间
     *
     * @param key
     *            键名
     *
     * @return 生存时间
     */
    public Long pttlKey(String key){
        try {
            return jedisCluster.pttl(key);
        } catch (Exception e) {
            log.error("redis to pttl key[{}]:{}", key, e.getMessage());
            return  -2L;
        }
    }

    /**
     * 保存一个hash哈希
     *
     * @param key
     *            hash的key值
     * @param hash
     *            hash值
     * @param milliseconds
     *            生存时间 (ms);小于或等于0，则不设置生存时间
     */
    public boolean hmset(String key, Map<String, String> hash, long milliseconds){
        try {
            if (milliseconds <= 0) {
                // 不设置 生存时间
                return "OK".equals(jedisCluster.hmset(key, hash));
            } else {
                jedisCluster.hmset(key, hash);
                // 设置 生存时间
                return 1L == jedisCluster.pexpire(key, milliseconds);
            }
        } catch (Exception e) {
            log.error("redis to hmset[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 往hash哈希中插入一个字段
     *
     * @param key
     *            键名
     * @param field
     *            字段名
     * @param value
     *            字段值
     * @return 是否插入成功
     */
    public boolean hset(String key, String field, String value) {
        try {
            return 1L == jedisCluster.hset(key, field, value);
        } catch (Exception e) {
            log.error("redis to hset[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 获取hash哈希值
     *
     * @param key
     *            键名
     * @return hash值
     */
    public Map<String, String> hgetAll(String key) {
        try {
            return jedisCluster.hgetAll(key);
        } catch (Exception e) {
            log.error("redis to hgetAll[{}]:{}", key, e.getMessage());
            return null;
        }
    }

    /**
     * 获取hash哈希中某个字段的值
     *
     * @param key
     *            键名
     * @param field
     *            字段名
     * @return 字段值
     */
    public String hget(String key, String field) {
        try {
            return jedisCluster.hget(key, field);
        } catch (Exception e) {
            log.error("redis to hget[{}]:{}", key, e.getMessage());
            return null;
        }
    }

    /**
     * 往list列表左边头部插入一个元素
     *
     * @param key
     *            列表的键名
     * @param value
     *            元素
     * @return 是否插入成功
     */
    public boolean lpush(String key, String value) {
        try {
            return 1L == jedisCluster.lpush(key, value);
        } catch (Exception e) {
            log.error("redis to lpush[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 往list列表右边尾部插入一个元素
     *
     * @param key
     *            列表的键名
     * @param value
     *            元素
     * @return 是否插入成功
     */
    public boolean rpush(String key, String value) {
        try {
            return 1L == jedisCluster.rpush(key, value);
        } catch (Exception e) {
            log.error("redis to rpush[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 往list列表左边头部弹出一个元素
     *
     * @param key
     *            列表的键名
     * @return 是否插入成功
     */
    public String lpop(String key) {
        try {
            return jedisCluster.lpop(key);
        } catch (Exception e) {
            log.error("redis to lpop[{}]:{}", key, e.getMessage());
            return null;
        }
    }

    /**
     * 往list列表右边尾部弹出一个元素
     *
     * @param key
     *            列表的键名
     * @return 是否插入成功
     */
    public String rpop(String key, String value) {
        try {
            return jedisCluster.rpop(key);
        } catch (Exception e) {
            log.error("redis to rpop[{}]:{}", key, e.getMessage());
            return null;
        }
    }

    /**
     * 获取list列表中的指定位置的值
     *
     * @param key
     *            列表对应的键名
     * @param start
     *            开始位置(左边头部为0)
     * @param end
     *            结束位置(右边尾部为-1)
     * @return 列表中的值
     */
    public List<String> lrange(String key, long start, long end) {
        try {
            return jedisCluster.lrange(key, start, end);
        } catch (Exception e) {
            log.error("redis to lrange[{}]:{}", key, e.getMessage());
            return null;
        }
    }

    /**
     * 删除list列表中指定val值指定数量个数
     * 如果count为负数则取相反数
     * 如果count为0则全部删除
     * 如果count大于val的个数则全部删除
     *
     * @param key
     *            列表对应的键名
     * @param count
     *            删除的数量
     * @param value
     *            指定value
     * @return 是否删除成功
     */
    public boolean lrem(String key, long count, String value) {
        try {
            return jedisCluster.lrem(key, count, value) > 0;
        } catch (Exception e) {
            log.error("redis to lrem[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 往set集合中添加成员
     *
     * @param key
     *            集合对应的键名
     * @param member
     *            成员
     * @return 是否添加成功
     */
    public boolean sadd(String key, String member) {
        try {
            return 1L == jedisCluster.sadd(key, member);
        } catch (Exception e) {
            log.error("redis to sadd[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 删除set集合中member成员
     *
     * @param key
     *            集合对应的键名
     * @param member
     *            成员
     * @return 是否添加成功
     */
    public boolean srem(String key, String member) {
        try {
            return 1L == jedisCluster.srem(key, member);
        } catch (Exception e) {
            log.error("redis to srem[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 获取set集合中所有member成员
     *
     * @param key
     *            集合对应的键名
     * @return 集合数据
     */
    public Set<String> smembers(String key) {
        try {
            return jedisCluster.smembers(key);
        } catch (Exception e) {
            log.error("redis to smembers[{}]:{}", key, e.getMessage());
            return null;
        }
    }

    /**
     * 获取多个set集合中的并集
     *
     * @param keys
     *            多个集合的键名数组
     * @return 并集
     */
    public Set<String> sinter(String... keys) {
        try {
            return jedisCluster.sinter(keys);
        } catch (Exception e) {
            log.error("redis to sinter[{}]:{}", keys, e.getMessage());
            return null;
        }
    }

    /**
     * 获取多个set集合中的交集
     *
     * @param keys
     *            多个集合的键名数组
     * @return 交集
     */
    public Set<String> sunion(String... keys) {
        try {
            return jedisCluster.sunion(keys);
        } catch (Exception e) {
            log.error("redis to sunion[{}]:{}", keys, e.getMessage());
            return null;
        }
    }

    /**
     * 获取多个set集合中的差集
     *
     * @param keys
     *            多个集合的键名数组
     * @return 差集
     */
    public Set<String> sdiff(String... keys) {
        try {
            return jedisCluster.sdiff(keys);
        } catch (Exception e) {
            log.error("redis to sdiff[{}]:{}", keys, e.getMessage());
            return null;
        }
    }

    /**
     * 往sorted set有序集合插入数据
     *
     * @param key
     *            有序集合的键名
     * @param scoreMembers
     *            数据
     * @return 是否插入成功
     */
    public boolean zadd(String key, Map<String, Double> scoreMembers) {
        try {
            return 1L == jedisCluster.zadd(key, scoreMembers);
        } catch (Exception e) {
            log.error("redis to zadd[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 删除sorted set有序集合的成员
     *
     * @param key
     *            有序集合的键名
     * @param member
     *            成员
     * @return 是否删除成功
     */
    public boolean zrem(String key, String... member) {
        try {
            return jedisCluster.zrem(key, member) > 0;
        } catch (Exception e) {
            log.error("redis to zrem[{}]:{}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 获取sorted set有序集合中某个成员的坐标(score从大到小排序，第一为0)
     *
     * @param key
     *            有序集合的键名
     * @param member
     *            成员
     * @return 成员坐标
     */
    public Long zrevrank(String key, String member) {
        try {
            return jedisCluster.zrevrank(key, member);
        } catch (Exception e) {
            log.error("redis to zrevrank[{}]:{}", key, e.getMessage());
            return null;
        }
    }

}
