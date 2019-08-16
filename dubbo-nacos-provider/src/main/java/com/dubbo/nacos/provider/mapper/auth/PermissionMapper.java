package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.DnPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Permission mapper
 *
 * @author 胡桃夹子
 * @date 2019-08-01 00:09
 */
@Mapper
public interface PermissionMapper {

    /**
     * insert Permission
     *
     * @param dnPermission
     */
    int insert(DnPermission dnPermission);

    /**
     * select Permission by id
     *
     * @param id
     */
    DnPermission selectByPrimaryKey(Long id);

    /**
     * update Permission by selective
     *
     * @param dnPermission
     */
    int updateByPrimaryKeySelective(DnPermission dnPermission);

    /**
     * find DnPermission by userId
     *
     * @param userId   DnUser.id
     * @param siteCode DnPermission.siteCode
     * @return List DnPermission
     */
    List<DnPermission> findPermissionByUserId(@Param("userId") Long userId, @Param("siteCode") String siteCode);
}