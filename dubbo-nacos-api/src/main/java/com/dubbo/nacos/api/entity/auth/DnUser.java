package com.dubbo.nacos.api.entity.auth;


import com.dubbo.nacos.api.entity.DnBaseEntity;
import lombok.Data;


/**
 * User
 *
 * @author 胡桃夹子
 * @date 2019-07-31 20:01
 */
@Data
public class DnUser extends DnBaseEntity {

    private static final long serialVersionUID = -1083711780724721275L;

    /**
     * PK, length is 19
     **/
    private Long id;

    /**
     * user login account
     **/
    private String account;

    /**
     * user login password
     **/
    private String password;

    /**
     * user real name
     **/
    private String realName;

    /**
     * user email
     **/
    private String email;

    /**
     * user mobile
     **/
    private String mobile;

    /**
     * salt code , associated with a password
     **/
    private String salt;

    public DnUser() {
        super();
    }

    public DnUser(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public DnUser(Long id, String account, String password, String realName, String email, String mobile, String salt) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.realName = realName;
        this.email = email;
        this.mobile = mobile;
        this.salt = salt;
    }
}