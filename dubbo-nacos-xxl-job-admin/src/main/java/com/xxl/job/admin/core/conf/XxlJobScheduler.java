package com.xxl.job.admin.core.conf;

import com.xxl.job.admin.core.thread.JobFailMonitorHelper;
import com.xxl.job.admin.core.thread.JobRegistryMonitorHelper;
import com.xxl.job.admin.core.thread.JobScheduleHelper;
import com.xxl.job.admin.core.thread.JobTriggerPoolHelper;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.biz.impl.ExecutorBizImpl;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.rpc.remoting.invoker.XxlRpcInvokerFactory;
import com.xxl.rpc.remoting.invoker.call.CallType;
import com.xxl.rpc.remoting.invoker.reference.XxlRpcReferenceBean;
import com.xxl.rpc.remoting.invoker.route.LoadBalance;
import com.xxl.rpc.remoting.net.NetEnum;
import com.xxl.rpc.remoting.net.impl.servlet.server.ServletServerHandler;
import com.xxl.rpc.remoting.provider.XxlRpcProviderFactory;
import com.xxl.rpc.serialize.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author xuxueli 2018-10-28 00:18:17
 */
@Component
@DependsOn("xxlJobAdminConfig")
public class XxlJobScheduler implements InitializingBean, DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(XxlJobScheduler.class);


    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info(">>>>>>>>> init xxl-job admin");
        // init i18n
        initI18n();

        // admin registry monitor run
        JobRegistryMonitorHelper.getInstance().start();

        // admin monitor run
        JobFailMonitorHelper.getInstance().start();

        // admin-server
        initRpcProvider();

        // start-schedule
        JobScheduleHelper.getInstance().start();

        logger.info(">>>>>>>>> init xxl-job admin success.");
        // try {
        //     TimeUnit.SECONDS.sleep(10);  // wait
        // } catch (InterruptedException e) {
        //     logger.error(e.getMessage(), e);
        // }
    }

    @Override
    public void destroy() throws Exception {
        logger.info(">>>>>>>>> destroy xxl-job admin");
        // stop-schedule
        JobScheduleHelper.getInstance().toStop();

        // admin trigger pool stop
        JobTriggerPoolHelper.toStop();

        // admin registry stop
        JobRegistryMonitorHelper.getInstance().toStop();

        // admin monitor stop
        JobFailMonitorHelper.getInstance().toStop();

        // admin-server
        stopRpcProvider();
    }

    // ---------------------- I18n ----------------------

    private void initI18n() {
        for (ExecutorBlockStrategyEnum item : ExecutorBlockStrategyEnum.values()) {
            item.setTitle(I18nUtil.getString("jobconf_block_".concat(item.name())));
        }
    }

    // ---------------------- admin rpc provider (no server version) ----------------------
    private static ServletServerHandler servletServerHandler;

    private void initRpcProvider() {
        // init
        XxlRpcProviderFactory xxlRpcProviderFactory = new XxlRpcProviderFactory();
        xxlRpcProviderFactory.initConfig(
                NetEnum.NETTY_HTTP,
                Serializer.SerializeEnum.HESSIAN.getSerializer(),
                null,
                0,
                XxlJobAdminConfig.getAdminConfig().getAccessToken(),
                null,
                null);

        // add services
        xxlRpcProviderFactory.addService(AdminBiz.class.getName(), null, XxlJobAdminConfig.getAdminConfig().getAdminBiz());

        // servlet handler
        servletServerHandler = new ServletServerHandler(xxlRpcProviderFactory);
    }

    private void stopRpcProvider() throws Exception {
        XxlRpcInvokerFactory.getInstance().stop();
    }

    public static void invokeAdminService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        servletServerHandler.handle(null, request, response);
    }


    // ---------------------- executor-client ----------------------
    private static ConcurrentMap<String, ExecutorBiz> executorBizRepository = new ConcurrentHashMap<String, ExecutorBiz>();

    /**
     * xxl-job default getExecutorBiz
     */
    public static ExecutorBiz getExecutorBiz(String address) throws Exception {
        // valid
        if (address == null || address.trim().length() == 0) {
            return null;
        }

        // load-cache
        address = address.trim();
        ExecutorBiz executorBiz = executorBizRepository.get(address);
        if (executorBiz != null) {
            return executorBiz;
        }

        // set-cache
        executorBiz = (ExecutorBiz) new XxlRpcReferenceBean(
                NetEnum.NETTY_HTTP,
                Serializer.SerializeEnum.HESSIAN.getSerializer(),
                CallType.SYNC,
                LoadBalance.ROUND,
                ExecutorBiz.class,
                null,
                3000,
                address,
                XxlJobAdminConfig.getAdminConfig().getAccessToken(),
                null,
                null).getObject();

        executorBizRepository.put(address, executorBiz);
        return executorBiz;
    }

    /**
     * 创建dubbo执行器
     *
     * @param address
     * @return
     * @throws Exception
     */
    public static ExecutorBiz getDubboEecutorBiz(String address) throws Exception {
        // valid
        if (address == null || address.trim().length() == 0) {
            return null;
        }

        // load-cache
        address = address.trim();
        // String key = "zk.address." + address;
        String key = "nacos.address." + address;
        ExecutorBiz executorBiz = executorBizRepository.get(key);
        if (executorBiz != null) {
            return executorBiz;
        }
        executorBiz = new ExecutorBizImpl(address);
        if (logger.isDebugEnabled()) {
            logger.debug("# key={},  address={}", key, address);
        }
        executorBizRepository.put(key, executorBiz);
        return executorBiz;
    }


}
