package com.dubbo.nacos.api.service.auth;

import com.dubbo.nacos.api.entity.auth.Role;
import com.dubbo.nacos.api.entity.auth.User;
import com.dubbo.nacos.api.exception.DnBusinessException;

/**
 * dubbo nacos authenticate related interface
 *
 * @author 胡桃夹子
 * @date 2019-07-28 10:47
 */
public interface DnAuthSerice {

    /**
     * find Role by roleCode
     *
     * @param roleCode roleCode
     * @return Role
     */
    public Role findRoleByRoleCode(String roleCode);

    /**
     * find User by account
     *
     * @param account User.account
     * @return User
     */
    public User findUserByAccount(String account);

    /**
     * add User
     *
     * @param user
     * @return
     */
    public boolean addUser(User user);

    /**
     * authorization
     *
     * @param userId User.id
     * @param roleId Role.id
     * @return
     */
    public boolean authorizing(Long userId, Long roleId);

    /**
     * user login
     *
     * @param account  User.account
     * @param password User.password
     * @return User
     */
    public User login(String account, String password) throws DnBusinessException;

}
