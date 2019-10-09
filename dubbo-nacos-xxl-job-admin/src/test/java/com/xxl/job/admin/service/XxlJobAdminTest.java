package com.xxl.job.admin.service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.dubbo.nacos.api.constants.DnConstants;
import com.dubbo.nacos.api.entity.auth.DnRole;
import com.dubbo.nacos.api.service.auth.DnAuthService;
import com.xxl.job.admin.XxlJobAdminApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * xxl-job-admin junit test
 *
 * @author 胡桃夹子
 * @date 2019-10-09 14:46
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = XxlJobAdminApplication.class)
public class XxlJobAdminTest {

    @Reference(interfaceClass = DnAuthService.class, version = "1.0")
    private DnAuthService dnAuthService;

    // @Autowired
    // private ReferenceConfig<GenericService> referenceConfig;

    // dubbo泛化调用
    private ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();

    @Test
    public void findRoleByRoleCode() {
        try {
            DnRole dnRole = dnAuthService.findRoleByRoleCode(DnConstants.ROLE_FOR_ADMIN);
            log.info("# role={}", JSON.toJSONString(dnRole));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void genericServiceTest() {
        try {
            String jobName = "com.dubbo.nacos.api.service.job.DnJobService";
            String versionNum = "1.0";
            String methodName = "executeTask";

            ApplicationConfig application = new ApplicationConfig();
            application.setName("dubbo-nacos-xxl-job-admin");

            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setProtocol("dubbo");

            referenceConfig.setApplication(application);
            referenceConfig.setRegistry(new RegistryConfig("nacos://127.0.0.1:8848"));

            // 声明为泛化接口
            referenceConfig.setGeneric(true);

            // 弱类型接口名
            referenceConfig.setInterface(jobName);
            referenceConfig.setVersion(versionNum);
            // s=》ms



            // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
            // ReferenceConfigCache configCache = ReferenceConfigCache.getCache();
            // if(null != configCache && configCache.get())
            GenericService genericService = ReferenceConfigCache.getCache().get(referenceConfig);

            // 引用远程服务
            // ReferenceConfig<GenericService> reference = new ReferenceConfig<>(); // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
            // reference.setInterface(jobName); // 弱类型接口名
            // reference.setVersion(versionNum);
            // reference.setGeneric(true); // 声明为泛化接口
            // reference.setApplication(referenceConfig.getApplication());
            // reference.setRegistry(referenceConfig.getRegistry());

            // GenericService genericService = referenceConfig.get(); // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用


            Object result = genericService.$invoke(methodName, null, null);
            log.info("# result={}", result);
            log.info(JSON.toJSONString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
