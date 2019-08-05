package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * UserRole mapper
 *
 * @author 胡桃夹子
 * @date 2019-08-01 00:13
 */
@Mapper
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

    /**
     * select UserRole by userId and roleId
     *
     * @param userId User.id
     * @param roleId Role.id
     * @return UserRole
     */
    UserRole selectUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}