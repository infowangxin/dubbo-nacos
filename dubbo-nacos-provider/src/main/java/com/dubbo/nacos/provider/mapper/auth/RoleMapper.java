package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Role mapper
 *
 * @author 胡桃夹子
 * @date 2019-08-01 00:10
 */
@Mapper
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

    /**
     * find Role by roleCode
     *
     * @param roleCode Role.roleCode
     * @return Role
     */
    Role findRoleByRoleCode(@Param("roleCode") String roleCode);
}