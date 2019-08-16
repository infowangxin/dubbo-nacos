package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.DnUserRole;
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
     * @param dnUserRole
     */
    int insert(DnUserRole dnUserRole);

    /**
     * select UserRole by id
     *
     * @param id
     */
    DnUserRole selectByPrimaryKey(Long id);

    /**
     * update UserRole by selective
     *
     * @param dnUserRole
     */
    int updateByPrimaryKeySelective(DnUserRole dnUserRole);

    /**
     * select UserRole by userId and roleId
     *
     * @param userId User.id
     * @param roleId Role.id
     * @return UserRole
     */
    DnUserRole selectUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}