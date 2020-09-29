package com.fullmoon.study.aop;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PerformanceMonitor {

    private static ThreadLocal<MethodPerformance> performanceRecord = new ThreadLocal<MethodPerformance>();

    public static void begin(String method){
        log.info("begin monitor...");
        MethodPerformance mp = new MethodPerformance(method);
        performanceRecord.set(mp);
    }

    public static void end(){
        log.info("end monitor...");
        MethodPerformance mp = performanceRecord.get();

        mp.printPerformance();
    }
}
