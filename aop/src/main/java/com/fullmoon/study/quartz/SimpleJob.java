package com.fullmoon.study.quartz;

import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

@Log4j2
public class SimpleJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("{} triggered. time is :{}", jobExecutionContext.getTrigger().getCalendarName(), new Date());
    }
}
