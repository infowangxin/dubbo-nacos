package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.DnRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param dnRole
     */
    int insert(DnRole dnRole);

    /**
     * select role by id
     *
     * @param id
     */
    DnRole selectByPrimaryKey(Long id);

    /**
     * update role by selective
     *
     * @param dnRole
     */
    int updateByPrimaryKeySelective(DnRole dnRole);

    /**
     * find Role by roleCode
     *
     * @param roleCode Role.roleCode
     * @return Role
     */
    DnRole findRoleByRoleCode(@Param("roleCode") String roleCode);

    /**
     * find DnRole by userId
     *
     * @param userId DnUser.id
     * @return List DnRole
     */
    List<DnRole> findRoleByUserId(@Param("userId") Long userId);
}