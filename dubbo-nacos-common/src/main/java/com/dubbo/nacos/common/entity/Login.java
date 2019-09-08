/**
 *
 */
package com.dubbo.nacos.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Login entity
 * @author 胡桃夹子
 * @date 2019/9/7 15:56
 */
@Data
public class Login implements Serializable {

    private static final long serialVersionUID = -7113928932182798316L;

    private String username;
    private String password;
    private Boolean rememberMe = false;

    public Login() {

    }

    public Login(String username, String password, Boolean rememberMe) {
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
    }

}
