package com.dubbo.nacos.provider.service.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.nacos.api.constants.DnConstants;
import com.dubbo.nacos.api.entity.auth.DnPermission;
import com.dubbo.nacos.api.entity.auth.DnRole;
import com.dubbo.nacos.api.entity.auth.DnUser;
import com.dubbo.nacos.api.entity.auth.DnUserRole;
import com.dubbo.nacos.api.enums.DnTableEnum;
import com.dubbo.nacos.api.exception.DnBusinessException;
import com.dubbo.nacos.api.service.auth.DnAuthService;
import com.dubbo.nacos.common.utils.salt.Digests;
import com.dubbo.nacos.common.utils.salt.Encodes;
import com.dubbo.nacos.provider.framework.id.IdGenerator;
import com.dubbo.nacos.provider.mapper.auth.PermissionMapper;
import com.dubbo.nacos.provider.mapper.auth.RoleMapper;
import com.dubbo.nacos.provider.mapper.auth.UserMapper;
import com.dubbo.nacos.provider.mapper.auth.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * authenticate related interface
 *
 * @author 胡桃夹子
 * @date 2019-08-01 00:14
 */
@Slf4j
@Service
public class DnAuthServiceImpl implements DnAuthService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    protected IdGenerator idGenerator;

    @Override
    public DnRole findRoleByRoleCode(String roleCode) {
        log.info("# findRoleByRoleCode roleCode={}", roleCode);
        if (StringUtils.isBlank(roleCode)) {
            log.error("# findRoleByRoleCode error , roleCode is empty.");
            return null;
        }
        DnRole dnRole = roleMapper.findRoleByRoleCode(roleCode);
        return dnRole;
    }

    @Override
    public DnUser findUserByAccount(String account) {
        log.info("# findUserByAccount account={}", account);
        if (StringUtils.isBlank(account)) {
            log.error("# findUserByAccount error , account is empty.");
        }
        DnUser dnUser = userMapper.findUserByAccount(account);
        return dnUser;
    }

    /**
     * get salt
     *
     * @return DnUser.salt
     */
    private String getSalt() {
        byte[] salt = Digests.generateSalt(DnConstants.SALT_SIZE);
        return Encodes.encodeHex(salt);
    }

    /**
     * get ciphertext
     *
     * @param password login password
     * @param salt     DnUser.salt
     * @return ciphertext string
     */
    private String getCiphertext(String password, String salt) {
        byte[] bytes = Encodes.decodeHex(salt);
        byte[] hashPassword = Digests.sha1(password.getBytes(), bytes, DnConstants.HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

    @Override
    public boolean addUser(DnUser dnUser) {
        if (null == dnUser) {
            log.error("# addUser error , dnUser is null.");
            return false;
        }

        if (StringUtils.isAnyBlank(dnUser.getAccount(), dnUser.getPassword(), dnUser.getRealName())) {
            log.error("# addUser error , account or password or realName is empty.");
            return false;
        }

        String salt = getSalt();
        String ciphertext = getCiphertext(dnUser.getPassword(), salt);
        dnUser.setSalt(salt);
        dnUser.setPassword(ciphertext);
        dnUser.setId(idGenerator.nextUniqueId(DnTableEnum.user));
        log.info("# dnUser={}", dnUser);
        int ret = userMapper.insert(dnUser);
        log.info("# addUser ret={}", ret);
        if (ret == 0) {
            log.info("# addUser error , dnUser={}", dnUser);
            return false;
        }
        return true;
    }

    @Override
    public boolean authorizing(Long userId, Long roleId) {
        log.info("# authorizing userId={},roleId={}", userId, roleId);
        if (null == userId || null == roleId) {
            log.error("# authorizing false , userId or roleId is empty");
            return false;
        }
        DnUser dnUser = userMapper.selectByPrimaryKey(userId);
        if (null == dnUser) {
            log.error("# authorizing false , don't find DnUser , userId={}", userId);
            return false;
        }
        DnRole dnRole = roleMapper.selectByPrimaryKey(roleId);
        if (null == dnRole) {
            log.error("# authorizing false , don't find DnRole , roleId={}", roleId);
            return false;
        }
        DnUserRole dnUserRole = userRoleMapper.selectUserRole(userId, roleId);
        if (null != dnUserRole) {
            log.info("# the previous authorizing was successful");
            return true;
        } else {
            dnUserRole = new DnUserRole(idGenerator.nextUniqueId(DnTableEnum.user_role), userId, roleId);
            int ret = userRoleMapper.insert(dnUserRole);
            if (1 == ret) {
                log.info("# authorizing successful");
                return true;
            }
            return false;
        }
    }

    @Override
    public DnUser login(String account, String password) throws DnBusinessException {
        log.info("# login account={}", account);
        if (StringUtils.isAnyBlank(account, password)) {
            log.error("# login error , account or password is empty.");
        }
        DnUser dnUser = findUserByAccount(account);
        if (null == dnUser) {
            log.error("# login error , don't find DnUser");
            return null;
        }

        String ciphertext = getCiphertext(password, dnUser.getSalt());
        if (!StringUtils.equals(dnUser.getPassword(), ciphertext)) {
            log.error("# login error , password error");
            return null;
        }
        return dnUser;
    }

    @Override
    public List<DnPermission> findPermissionByUserId(Long userId, String siteCode) {
        log.info("# findPermissionByUserId userId={},siteCode={}", userId, siteCode);
        if (null == userId || StringUtils.isBlank(siteCode)) {
            log.error("# findPermissionByUserId error , userId  or siteCode is empty.");
            return null;
        }
        return permissionMapper.findPermissionByUserId(userId, siteCode);
    }

    @Override
    public List<DnRole> findRoleByUserId(Long userId) {
        log.info("# findRoleByUserId userId={}", userId);
        if (null == userId) {
            log.error("# findPermissionByUserId error , userId is empty.");
            return null;
        }
        return roleMapper.findRoleByUserId(userId);
    }
}
