package com.xxl.job.admin.core.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.biz.model.LogResult;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author: liqiang7
 * @date: 2019/1/11 17:52
 * @description:
 */
public class HandlerReference implements ExecutorBiz {

    private static Logger logger = LoggerFactory.getLogger(HandlerReference.class);

    private static boolean enableBadMap = false;

    @Value("${spring.dubbo.application.name}")
    private String applicationName;



    private volatile ReferenceConfig<ExecutorBiz> referenceConfig;

    private volatile ExecutorBiz executorBiz;

    public HandlerReference(String group, String ip, String port, RegistryConfig registryConfig) {
        init(group, ip, port, registryConfig);
    }

    //初始化配置
    synchronized void init(String group, String ip, String port, RegistryConfig registryConfig) {
        this.referenceConfig = createReference(group, ip, port, registryConfig);
        this.executorBiz = this.referenceConfig.get();
    }

    private ReferenceConfig<ExecutorBiz> createReference(String group, String ip, String port, RegistryConfig registryConfig) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("xxl-job-admin");
        // 拼装url
        StringBuffer urlSb = new StringBuffer();
        urlSb.append("dubbo://");
        urlSb.append(ip);
        urlSb.append(":");
        urlSb.append(port);
        // 使用固定token
        urlSb.append("?token=harvestJob");

        ReferenceConfig<ExecutorBiz> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(application);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setGroup(group);
        referenceConfig.setUrl(urlSb.toString());
        referenceConfig.setInterface(ExecutorBiz.class);
        referenceConfig.setCheck(enableBadMap);
        referenceConfig.setTimeout(registryConfig.getTimeout());
        return referenceConfig;
    }


    @Override
    public ReturnT<String> beat() {
        if (this.executorBiz != null) {
            return this.executorBiz.beat();
        }
        throw new RuntimeException("executorBiz instance is empty");
    }

    @Override
    public ReturnT<String> idleBeat(int jobId) {
        if (this.executorBiz != null) {
            return this.executorBiz.idleBeat(jobId);
        }
        throw new RuntimeException("executorBiz instance is empty");
    }

    @Override
    public ReturnT<String> kill(int jobId) {
        if (this.executorBiz != null) {
            return this.executorBiz.kill(jobId);
        }
        throw new RuntimeException("executorBiz instance is empty");
    }

    @Override
    public ReturnT<LogResult> log(long logDateTim, long logId, int fromLineNum) {
        if (this.executorBiz != null) {
            return this.executorBiz.log(logDateTim, logId, fromLineNum);
        }
        throw new RuntimeException("executorBiz instance is empty");
    }

    @Override
    public ReturnT<String> run(TriggerParam triggerParam) {
        if (this.executorBiz != null) {
            return this.executorBiz.run(triggerParam);
        }
        throw new RuntimeException("executorBiz instance is empty");
    }
}
