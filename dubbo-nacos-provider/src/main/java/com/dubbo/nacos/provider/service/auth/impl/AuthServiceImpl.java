package com.dubbo.nacos.provider.service.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.nacos.api.entity.auth.Role;
import com.dubbo.nacos.api.entity.auth.User;
import com.dubbo.nacos.api.exception.BusinessException;
import com.dubbo.nacos.api.service.auth.AuthSerice;
import com.dubbo.nacos.provider.mapper.auth.RoleMapper;
import com.dubbo.nacos.provider.mapper.auth.UserMapper;
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
public class AuthServiceImpl implements AuthSerice {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

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

        //user.setId();

        return false;
    }

    @Override
    public boolean authorization(String userId, String roleId) {
        return false;
    }

    @Override
    public User login(String account, String password) throws BusinessException {
        log.info("# login account={}", account);
        if (StringUtils.isAnyBlank(account, password)) {
            log.error("# login error , account or password is empty.");
        }
        User user = findUserByAccount(account);
        if (null == user) {
            log.error("# login error");
            return null;
        }


        return user;
    }
}
