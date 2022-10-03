package com.bachdv.lib.common.task.service;

import com.bachdv.lib.common.task.model.BaseTask;
import com.bachdv.lib.common.task.model.QuartzJobDetail;
import org.quartz.SchedulerException;

/**
 * @author Marco.Duong
 */
public interface JobService<TASK extends BaseTask> {

    boolean addJob(QuartzJobDetail job) throws SchedulerException;

    boolean deleteJob(QuartzJobDetail job);

}
