package com.dubbo.nacos.api.entity.auth;


import com.dubbo.nacos.api.entity.BaseEntity;
import lombok.Data;

/**
 * Role
 *
 * @author 胡桃夹子
 * @date 2019-07-31 20:03
 */
@Data
public class Role extends BaseEntity {

    private static final long serialVersionUID = -6982490361440305761L;

    /**
     * PK, length is 19
     **/
    private Long id;

    /**
     * role name
     **/
    private String roleName;

    /**
     * role code
     **/
    private String roleCode;

    /**
     * parent role Pk
     **/
    private String parentId;

    /**
     * roleType
     **/
    private String roleType;

    /**
     * remark
     **/
    private String remark;

    public Role() {
        super();
    }

    public Role(Long id, String roleName, String roleCode, String parentId, String roleType, String remark) {
        this.id = id;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.parentId = parentId;
        this.roleType = roleType;
        this.remark = remark;
    }
}
