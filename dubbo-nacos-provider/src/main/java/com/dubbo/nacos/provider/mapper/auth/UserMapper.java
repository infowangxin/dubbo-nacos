package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * User mapper
 *
 * @author 胡桃夹子
 * @date 2019-08-01 00:12
 */
@Mapper
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

    /**
     * find User by account
     *
     * @param account User.account
     * @return User
     */
    User findUserByAccount(@Param("account") String account);
}