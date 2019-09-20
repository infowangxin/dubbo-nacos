package com.dubbo.nacos.consumer.authenticate;

import com.dubbo.nacos.api.constants.DnConstants;
import com.dubbo.nacos.consumer.config.RedisConfig;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro config
 *
 * @author 胡桃夹子
 * @date 2019-08-07 21:44
 */
@Configuration
public class ShiroConfig {

    @Bean
    public RedisConfig redisConfig() {
        return new RedisConfig();
    }

    public RedisManager redisManager() {
        RedisConfig config = redisConfig();
        RedisManager manager = new RedisManager();
        manager.setHost(config.getHost() + ":" + config.getPort());
        manager.setTimeout(redisConfig().getTimeout());
        return manager;
    }

    public RedisCacheManager cacheManager() {
        /* crazycake 实现 */
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        return cacheManager;
    }

    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        /* crazycake 实现 */
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());

        /* Session ID 生成器 */
        sessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return sessionDAO;
    }

    @Bean
    public SimpleCookie simpleCookie() {
        /* cookie的name,对应的默认是 JSESSIONID */
        SimpleCookie cookie = new SimpleCookie(DnConstants.JSESSIONID);
        /* path为 / 用于多个系统共享JSESSIONID */
        cookie.setPath("/");
        cookie.setMaxAge(DnConstants.SHIRO_SESSION_ID_COOKIE_MAXAGE);
        return cookie;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        /* 设置session超时 */
        sessionManager.setGlobalSessionTimeout(DnConstants.SHIRO_SESSION_TIMEOUT);
        /* 删除无效session */
        sessionManager.setDeleteInvalidSessions(true);
        /* 设置JSESSIONID */
        sessionManager.setSessionIdCookie(simpleCookie());
        /* 设置sessionDAO */
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    @Bean
    public AuthorizingRealm customAuthorizingRealm() {
        AuthorizingRealm realm = new CustomAuthorizingRealm();
        return realm;
    }

    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * <dependency>
     *<groupId>org.springframework.boot</groupId>
     *<artifactId>spring-boot-starter-aop</artifactId>
     *</dependency>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager(customAuthorizingRealm());
        // 设置realm.
        manager.setRealm(customAuthorizingRealm());
        // 自定义缓存实现 使用redis
        manager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        manager.setSessionManager(sessionManager());
        // <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        // manager.setCacheManager(getEhCacheManager());
        manager.setSessionManager(sessionManager());
        return manager;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        // authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
        // anon：它对应的过滤器里面是空的,什么都没做

        /////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // log.info("##################从数据库读取权限规则，加载到shiroFilter中##################");

        // filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取

        // anon 可以理解为不拦截
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/build/**", "anon");
        filterChainDefinitionMap.put("/vendors/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");

        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/**", "authc");

        filterChainDefinitionMap.put("/logout", "logout");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * ShiroFilter<br/>
     * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new CustomShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的连接
        //shiroFilterFactoryBean.setSuccessUrl("/user");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }


}
