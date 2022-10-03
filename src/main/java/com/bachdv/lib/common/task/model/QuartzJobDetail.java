package com.bachdv.lib.common.task.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author Marco.Duong
 */
@Setter
@Getter
public class QuartzJobDetail {

    private String jobId;
    private String jobName;
    private String jobGroup;
    private String jobClass;
    private String description;
    private String cronExpression;
    private boolean isDisabled;
    private Map<String, Object> dataMap;

}
