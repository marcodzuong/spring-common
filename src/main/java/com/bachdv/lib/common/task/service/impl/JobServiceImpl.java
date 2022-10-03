package com.bachdv.lib.common.task.service.impl;

import com.bachdv.lib.common.task.model.QuartzJobDetail;
import com.bachdv.lib.common.task.service.JobService;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Marco.Duong
 */
public class JobServiceImpl implements JobService {

    private final Scheduler scheduler;

    @Autowired
    public JobServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }


    @Override
    public boolean addJob(QuartzJobDetail job) throws SchedulerException {
        if (job == null || job.isDisabled()) {
            return false;
        }
        if (!isValidExpression(job.getCronExpression())) {
            return false;
        } else {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                //  schedule new Job
                JobDetail jobDetail = JobBuilder.newJob(BaseJob.class).withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("job", job);
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                //  reschedule Job
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
        return true;
    }

    @Override
    public boolean deleteJob(QuartzJobDetail job) {
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        try {
            scheduler.deleteJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isValidExpression(final String cronExpression) {
        try {
            CronTriggerImpl trigger = new CronTriggerImpl();
            trigger.setCronExpression(cronExpression);
            Date date = trigger.computeFirstFireTime(null);
            return date != null && date.after(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

}
