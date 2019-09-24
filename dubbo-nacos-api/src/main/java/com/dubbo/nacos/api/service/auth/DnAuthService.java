package com.dubbo.nacos.api.service.auth;

import com.dubbo.nacos.api.entity.auth.DnPermission;
import com.dubbo.nacos.api.entity.auth.DnRole;
import com.dubbo.nacos.api.entity.auth.DnUser;
import com.dubbo.nacos.api.exception.DnBusinessException;

import java.util.List;

/**
 * dubbo nacos authenticate related interface
 *
 * @author 胡桃夹子
 * @date 2019-07-28 10:47
 */
public interface DnAuthService {

    /**
     * find DnRole by roleCode
     *
     * @param roleCode DnRole.roleCode
     * @return DnRole
     */
    public DnRole findRoleByRoleCode(String roleCode);

    /**
     * find DnUser by account
     *
     * @param account DnUser.account
     * @return DnUser
     */
    public DnUser findUserByAccount(String account);

    /**
     * add DnUser
     *
     * @param dnUser
     * @return
     */
    public boolean addUser(DnUser dnUser);

    /**
     * authorization
     *
     * @param userId DnUser.id
     * @param roleId DnRole.id
     * @return
     */
    public boolean authorizing(Long userId, Long roleId);

    /**
     * user login
     *
     * @param account  DnUser.account
     * @param password DnUser.password
     * @return DnUser
     */
    public DnUser login(String account, String password) throws DnBusinessException;

    /**
     * find DnPermission by userId
     *
     * @param userId   DnUser.id
     * @param siteCode DnPermission.siteCode
     * @return List DnPermission
     */
    public List<DnPermission> findPermissionByUserId(Long userId, String siteCode);

    /**
     * find DnRole by userId
     *
     * @param userId DnUser.id
     * @return List DnRole
     */
    List<DnRole> findRoleByUserId(Long userId);
}
