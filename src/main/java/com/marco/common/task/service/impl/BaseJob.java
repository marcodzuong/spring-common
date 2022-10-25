package com.marco.common.task.service.impl;

import com.marco.common.task.model.QuartzJobDetail;
import com.marco.common.task.service.JobExecutor;
import com.marco.common.util.SpringUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Marco.Duong
 */
public class BaseJob implements Job {

    private static void executeJob(QuartzJobDetail jobDetail) throws Exception {
        JobExecutor jobExecutor = null;
        Class<?> clazz;
        if (jobDetail.getJobClass() != null && !jobDetail.getJobClass().isEmpty()) {
            clazz = Class.forName(jobDetail.getJobClass());
            jobExecutor = (JobExecutor) SpringUtil.getBean(clazz);
        }
        if (jobExecutor != null) {
            jobExecutor.execute(jobDetail);
        }
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        try {
            QuartzJobDetail job = (QuartzJobDetail) data.get("job");
            executeJob(job);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
