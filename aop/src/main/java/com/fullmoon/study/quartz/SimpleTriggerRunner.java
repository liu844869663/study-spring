package com.fullmoon.study.quartz;

import org.quartz.*;

public class SimpleTriggerRunner {
    public static void  main(String[] args){
        JobDetail jobDetail = new JobDetail() {
            public JobKey getKey() {
                return null;
            }

            public String getDescription() {
                return null;
            }

            public Class<? extends Job> getJobClass() {
                return null;
            }

            public JobDataMap getJobDataMap() {
                return null;
            }

            public boolean isDurable() {
                return false;
            }

            public boolean isPersistJobDataAfterExecution() {
                return false;
            }

            public boolean isConcurrentExectionDisallowed() {
                return false;
            }

            public boolean requestsRecovery() {
                return false;
            }

            public Object clone() {
                return null;
            }

            public JobBuilder getJobBuilder() {
                return null;
            }
        };
    }
}
