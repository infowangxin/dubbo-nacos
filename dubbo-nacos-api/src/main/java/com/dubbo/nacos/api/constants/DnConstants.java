package com.dubbo.nacos.api.constants;

/**
 * dubbo nacos constants
 *
 * @author 胡桃夹子
 * @date 2019-08-05 22:20
 */
public class DnConstants {


    // role relates
    public static final String ROLE_FOR_ADMIN = "admin";
    public static final String ROLE_FOR_CUSTOMER = "customer";

    /* site code */
    public static final String DUBBO_NACOS_CONSUMER = "dubbo-nacos-consumer";

    // authorization relates
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    /* shiro session key */
    public static final String DN_PERMISSION_URL = "dn_permission_url";
    public static final String DN_ROLE_CODE = "dn_role_code";

    /* custom cookie key */
    public static final String JSESSIONID = "DNSESSIONID";
    /* 缓存命名空间 */
    public static final String SYSTEM_CACHE_NAMESPACE = "DN:";

    /* SESSION */
    public static final String REDIS_SHIRO_SESSION = SYSTEM_CACHE_NAMESPACE + "SHIRO-SESSION:";

    /* maxAge */
    public static final int SHIRO_SESSION_ID_COOKIE_MAXAGE = 2592000;

    /* session timeout , milliseconds */
    public static final int SHIRO_SESSION_TIMEOUT = 1800000;
}
