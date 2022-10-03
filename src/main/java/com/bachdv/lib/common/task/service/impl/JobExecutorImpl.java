package com.bachdv.lib.common.task.service.impl;

import com.bachdv.lib.common.task.model.QuartzJobDetail;
import com.bachdv.lib.common.task.service.JobExecutor;

import java.util.Date;
import java.util.Map;

/**
 * @author Marco.Duong
 */
public abstract class JobExecutorImpl implements JobExecutor {
    @Override
    public void execute(QuartzJobDetail detail) {
        try {
            Date executeAt = new Date();
            Map<String, Object> dataMap;

            if (detail != null) {
                dataMap = detail.getDataMap();
                executeMap(dataMap);
            }
            updateAfterExecuted(executeAt, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * implement detail in sub class
     */
    protected abstract void executeMap(Map<String, Object> data);

    /**
     * Update log or update db to Check when have bugs
     */
    protected abstract void updateAfterExecuted(Date executeAt, boolean isSuccess);

}
