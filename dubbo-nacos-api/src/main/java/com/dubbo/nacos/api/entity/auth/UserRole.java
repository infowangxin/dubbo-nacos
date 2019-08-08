package com.dubbo.nacos.api.entity.auth;


import com.dubbo.nacos.api.entity.BaseEntity;
import lombok.Data;

/**
 * User and Role relation
 *
 * @author 胡桃夹子
 * @date 2019-07-31 19:53
 */
@Data
public class UserRole extends BaseEntity {

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

    public UserRole() {
        super();
    }

    public UserRole(Long id, Long userId, Long roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }
}
