package com.dubbo.nacos.api.entity.auth;


import com.dubbo.nacos.api.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * User
 *
 * @author 胡桃夹子
 * @date 2019-07-31 20:01
 */
@Setter
@Getter
@ToString
public class User extends BaseEntity {

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

    public User() {
        super();
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User(Long id, String account, String password, String realName, String email, String mobile, String salt) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.realName = realName;
        this.email = email;
        this.mobile = mobile;
        this.salt = salt;
    }
}