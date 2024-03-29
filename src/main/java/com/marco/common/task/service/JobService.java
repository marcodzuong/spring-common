package com.marco.common.task.service;

import com.marco.common.task.model.QuartzJobDetail;
import org.quartz.SchedulerException;

/**
 * @author Marco.Duong
 */
public interface JobService {

    boolean addJob(QuartzJobDetail job) throws SchedulerException;

    boolean deleteJob(QuartzJobDetail job);

}
