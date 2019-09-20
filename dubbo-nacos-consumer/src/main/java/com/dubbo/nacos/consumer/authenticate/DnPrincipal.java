package com.dubbo.nacos.consumer.authenticate;

import com.dubbo.nacos.api.entity.auth.DnRole;
import com.dubbo.nacos.api.entity.auth.DnUser;
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

    private DnUser dnUser;

    private List<DnRole> dnRoles;

}
