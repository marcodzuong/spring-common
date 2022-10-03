package com.bachdv.lib.common.task.service;

import com.bachdv.lib.common.task.model.QuartzJobDetail;

/**
 * @author Marco.Duong
 */
public interface JobExecutor {

    void execute(QuartzJobDetail detail);
}
