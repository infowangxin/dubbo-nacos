package com.xxl.job.core.biz.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xuxueli on 16/7/22.
 */
@Data
public class TriggerParam implements Serializable {
    private static final long serialVersionUID = 42L;

    private int jobId;

    private String executorHandler;
    private String executorParams;
    private String executorDubboMethod;
    private String executorDubboVersion;
    private String executorBlockStrategy;
    private int executorTimeout;

    private long logId;
    private long logDateTim;

    private String glueType;
    private String glueSource;
    private long glueUpdatetime;

    private int broadcastIndex;
    private int broadcastTotal;


}
