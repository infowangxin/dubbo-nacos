package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.UserRole;

/**
 * UserRole mapper
 *
 * @author 胡桃夹子
 * @date 2019-08-01 00:13
 */
public interface UserRoleMapper {

    /**
     * insert UserRole
     *
     * @param userRole
     */
    int insert(UserRole userRole);

    /**
     * select UserRole by id
     *
     * @param id
     */
    UserRole selectByPrimaryKey(Long id);

    /**
     * update UserRole by selective
     *
     * @param userRole
     */
    int updateByPrimaryKeySelective(UserRole userRole);

}