package com.bachdv.lib.common.task.service.impl;

import com.bachdv.lib.common.task.model.BaseTask;
import com.bachdv.lib.common.task.model.QuartzJobDetail;
import com.bachdv.lib.common.task.service.JobService;
import org.quartz.SchedulerException;

/**
 * @author Marco.Duong
 */
public class JobServiceImpl<TASK extends BaseTask> implements JobService<TASK> {


    @Override
    public boolean addJob(QuartzJobDetail job) throws SchedulerException {
        return false;
    }

    @Override
    public boolean deleteJob(QuartzJobDetail job) {
        return false;
    }

}
