package com.marco.common.task.service;

import com.marco.common.task.model.QuartzJobDetail;

/**
 * @author Marco.Duong
 */
public interface JobExecutor {

    void execute(QuartzJobDetail detail);
}
