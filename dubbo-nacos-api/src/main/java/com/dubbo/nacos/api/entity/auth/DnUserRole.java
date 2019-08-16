package com.dubbo.nacos.api.entity.auth;


import com.dubbo.nacos.api.entity.DnBaseEntity;
import lombok.Data;

/**
 * User and Role relation
 *
 * @author 胡桃夹子
 * @date 2019-07-31 19:53
 */
@Data
public class DnUserRole extends DnBaseEntity {

    private static final long serialVersionUID = -56720255622342923L;

    /**
     * PK, length is 19
     **/
    private Long id;

    /**
     * user id, length is 19
     **/
    private Long userId;

    /**
     * role id, length is 19
     **/
    private Long roleId;

    public DnUserRole() {
        super();
    }

    public DnUserRole(Long id, Long userId, Long roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }
}
