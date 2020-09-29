package com.fullmoon.study.aop;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ForumServiceImpl implements ForumService {

    public void removeTopic(int topicId) {
        // PerformanceMonitor.begin("ForumServiceImpl.removeTopic");
        log.info("模拟删除Topic记录：" + topicId);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // PerformanceMonitor.end();
    }

    public void removeForum(int forumId) {
        // PerformanceMonitor.begin("ForumServiceImpl.removeForum");
        log.info("模拟删除Forum记录：" + forumId);
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // PerformanceMonitor.end();
    }
}
