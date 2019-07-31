package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.User;

/**
 * User mapper
 *
 * @author 胡桃夹子
 * @date 2019-08-01 00:12
 */
public interface UserMapper {

    /**
     * insert  User
     *
     * @param user
     */
    int insert(User user);

    /**
     * select User by id
     *
     * @param id
     */
    User selectByPrimaryKey(Long id);

    /**
     * update User by  selective
     *
     * @param user
     */
    int updateByPrimaryKeySelective(User user);

}