package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.Role;

/**
 * Role mapper
 *
 * @author 胡桃夹子
 * @date 2019-08-01 00:10
 */
public interface RoleMapper {
    /**
     * insert role
     *
     * @param role
     */
    int insert(Role role);

    /**
     * select role by id
     *
     * @param id
     */
    Role selectByPrimaryKey(Long id);

    /**
     * update role by selective
     *
     * @param role
     */
    int updateByPrimaryKeySelective(Role role);

}