package com.dubbo.nacos.api.entity.auth;

import com.dubbo.nacos.api.entity.DnBaseEntity;
import lombok.Data;

/**
 * Role and Permission relation
 *
 * @author 胡桃夹子
 * @date 2019-07-31 20:11
 */
@Data
public class DnRolePermission extends DnBaseEntity {

    private static final long serialVersionUID = -7948507636703811294L;

    /**
     * PK, length is 19
     **/
    private Long id;

    /**
     * role id, length is 19
     **/
    private Long roleId;

    /**
     * permission id, length is 19
     **/
    private Long permissionId;

    /**
     * site code
     **/
    private String siteCode;

    public DnRolePermission() {
        super();
    }

    public DnRolePermission(Long id, Long roleId, Long permissionId, String siteCode) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.siteCode = siteCode;
    }
}
