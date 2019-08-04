package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.Permission;
import org.apache.ibatis.annotations.Mapper;

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
     * @param permission
     */
    int insert(Permission permission);

    /**
     * select Permission by id
     *
     * @param id
     */
    Permission selectByPrimaryKey(Long id);

    /**
     * update Permission by selective
     *
     * @param permission
     */
    int updateByPrimaryKeySelective(Permission permission);

}