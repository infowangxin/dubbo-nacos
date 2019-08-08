package com.dubbo.nacos.consumer.authenticate;

import com.dubbo.nacos.api.entity.auth.Role;
import com.dubbo.nacos.api.entity.auth.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * principal model
 *
 * @author 胡桃夹子
 * @date 2019-08-07 21:57
 */
@Data
public class DnPrincipal implements Serializable {

    private static final long serialVersionUID = -3503040105003870792L;

    private User user;

    private List<Role> roles;

}
