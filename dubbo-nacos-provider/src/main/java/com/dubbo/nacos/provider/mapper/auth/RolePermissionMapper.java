package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.RolePermission;
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
     * @param rolePermission
     */
    int insert(RolePermission rolePermission);

    /**
     * select RolePermission by id
     *
     * @param id
     */
    RolePermission selectByPrimaryKey(Long id);

    /**
     * update RolePermission by selective
     *
     * @param rolePermission
     */
    int updateByPrimaryKeySelective(RolePermission rolePermission);

}