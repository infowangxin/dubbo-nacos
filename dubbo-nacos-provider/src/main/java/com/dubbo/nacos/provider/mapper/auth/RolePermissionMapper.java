package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.DnRolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * RolePermission mapper
 *
 * @author 胡桃夹子
 * @date 2019-08-01 00:11
 */
@Mapper
public interface RolePermissionMapper {
    /**
     * insert RolePermission
     *
     * @param dnRolePermission
     */
    int insert(DnRolePermission dnRolePermission);

    /**
     * select RolePermission by id
     *
     * @param id
     */
    DnRolePermission selectByPrimaryKey(Long id);

    /**
     * update RolePermission by selective
     *
     * @param dnRolePermission
     */
    int updateByPrimaryKeySelective(DnRolePermission dnRolePermission);

}