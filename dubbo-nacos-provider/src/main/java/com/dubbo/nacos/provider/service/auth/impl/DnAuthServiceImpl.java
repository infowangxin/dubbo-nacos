package com.dubbo.nacos.provider.service.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.nacos.api.constants.DnConstans;
import com.dubbo.nacos.api.entity.auth.Role;
import com.dubbo.nacos.api.entity.auth.User;
import com.dubbo.nacos.api.entity.auth.UserRole;
import com.dubbo.nacos.api.enums.DnTableEnum;
import com.dubbo.nacos.api.exception.DnBusinessException;
import com.dubbo.nacos.api.service.auth.DnAuthSerice;
import com.dubbo.nacos.common.utils.salt.Digests;
import com.dubbo.nacos.common.utils.salt.Encodes;
import com.dubbo.nacos.provider.framework.id.IdGenerator;
import com.dubbo.nacos.provider.mapper.auth.RoleMapper;
import com.dubbo.nacos.provider.mapper.auth.UserMapper;
import com.dubbo.nacos.provider.mapper.auth.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * authenticate related interface
 *
 * @author 胡桃夹子
 * @date 2019-08-01 00:14
 */
@Slf4j
@Service
public class DnAuthServiceImpl implements DnAuthSerice {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    protected IdGenerator idGenerator;

    @Override
    public Role findRoleByRoleCode(String roleCode) {
        log.info("# findRoleByRoleCode roleCode={}", roleCode);
        if (StringUtils.isBlank(roleCode)) {
            log.error("# findRoleByRoleCode error , roleCode is empty.");
            return null;
        }
        Role role = roleMapper.findRoleByRoleCode(roleCode);
        return role;
    }

    @Override
    public User findUserByAccount(String account) {
        log.info("# findUserByAccount account={}", account);
        if (StringUtils.isBlank(account)) {
            log.error("# findUserByAccount error , account is empty.");
        }
        User user = userMapper.findUserByAccount(account);
        return user;
    }

    /**
     * get salt
     *
     * @return User.salt
     */
    private String getSalt() {
        byte[] salt = Digests.generateSalt(DnConstans.SALT_SIZE);
        return Encodes.encodeHex(salt);
    }

    /**
     * get ciphertext
     *
     * @param password login password
     * @param salt     User.salt
     * @return ciphertext string
     */
    private String getCiphertext(String password, String salt) {
        byte[] bytes = Encodes.decodeHex(salt);
        byte[] hashPassword = Digests.sha1(password.getBytes(), bytes, DnConstans.HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

    @Override
    public boolean addUser(User user) {
        if (null == user) {
            log.error("# addUser error , user is null.");
            return false;
        }

        if (StringUtils.isAnyBlank(user.getAccount(), user.getPassword(), user.getRealName())) {
            log.error("# addUser error , account or password or realName is empty.");
            return false;
        }

        String salt = getSalt();
        String ciphertext = getCiphertext(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(ciphertext);
        user.setId(idGenerator.nextUniqueId(DnTableEnum.user));
        log.info("# user={}", user);
        int ret = userMapper.insert(user);
        log.info("# addUser ret={}", ret);
        if (ret == 0) {
            log.info("# addUser error , user={}", user);
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
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user) {
            log.error("# authorizing false , don't find user , userId={}", userId);
            return false;
        }
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (null == role) {
            log.error("# authorizing false , don't find role , roleId={}", roleId);
            return false;
        }
        UserRole userRole = userRoleMapper.selectUserRole(userId, roleId);
        if (null != userRole) {
            log.info("# the previous authorizing was successful");
            return true;
        } else {
            userRole = new UserRole(idGenerator.nextUniqueId(DnTableEnum.user_role), userId, roleId);
            int ret = userRoleMapper.insert(userRole);
            if (1 == ret) {
                log.info("# authorizing successful");
                return true;
            }
            return false;
        }
    }

    @Override
    public User login(String account, String password) throws DnBusinessException {
        log.info("# login account={}", account);
        if (StringUtils.isAnyBlank(account, password)) {
            log.error("# login error , account or password is empty.");
        }
        User user = findUserByAccount(account);
        if (null == user) {
            log.error("# login error , don't find user");
            return null;
        }

        String ciphertext = getCiphertext(password, user.getSalt());
        if (!StringUtils.equals(user.getPassword(), ciphertext)) {
            log.error("# login error , password error");
            return null;
        }
        return user;
    }
}
