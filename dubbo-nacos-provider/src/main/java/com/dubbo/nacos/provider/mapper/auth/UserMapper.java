package com.dubbo.nacos.provider.mapper.auth;

import com.dubbo.nacos.api.entity.auth.DnUser;
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
     * @param dnUser
     */
    int insert(DnUser dnUser);

    /**
     * select User by id
     *
     * @param id
     */
    DnUser selectByPrimaryKey(Long id);

    /**
     * update User by  selective
     *
     * @param dnUser
     */
    int updateByPrimaryKeySelective(DnUser dnUser);

    /**
     * find User by account
     *
     * @param account User.account
     * @return User
     */
    DnUser findUserByAccount(@Param("account") String account);
}