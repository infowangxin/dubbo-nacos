package com.dubbo.nacos.consumer.authenticate;

import com.dubbo.nacos.api.constants.DnConstants;
import com.dubbo.nacos.api.entity.auth.DnPermission;
import com.dubbo.nacos.api.entity.auth.DnRole;
import com.dubbo.nacos.api.entity.auth.DnUser;
import com.dubbo.nacos.api.exception.DnBusinessException;
import com.dubbo.nacos.api.service.auth.DnAuthSerice;
import com.dubbo.nacos.common.utils.salt.Encodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro customer authorizing realm
 *
 * @author 胡桃夹子
 * @date 2019-08-07 21:46
 */
@Slf4j
public class CustomerAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private DnAuthSerice dnAuthSerice;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        DnPrincipal principal = (DnPrincipal) principalCollection.fromRealm(getName()).iterator().next();
        Session session = SecurityUtils.getSubject().getSession();
        // ---
        Set<String> permissions = new HashSet<>();
        Object permissionObject = session.getAttribute(DnConstants.DN_PERMISSION_URL);
        if (null == permissionObject) {
            List<DnPermission> list = dnAuthSerice.findPermissionByUserId(principal.getDnUser().getId(), DnConstants.DUBBO_NACOS_CONSUMER);
            for (DnPermission permission : list) {
                permissions.add(permission.getUrl());
            }
            session.setAttribute(DnConstants.DN_PERMISSION_URL, permissions);
        } else {
            permissions = (Set<String>) permissionObject;
        }

        Set<String> roleCodes = new HashSet<>();
        Object roleCodeObject = session.getAttribute(DnConstants.DN_ROLE_CODE);
        if (null == roleCodeObject) {
            List<DnRole> list = dnAuthSerice.findRoleByUserId(principal.getDnUser().getId());
            for (DnRole role : list) {
                roleCodes.add(role.getRoleCode());
            }
            session.setAttribute(DnConstants.DN_ROLE_CODE, roleCodes);
        } else {
            roleCodes = (Set<String>) roleCodeObject;
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleCodes);
        info.setStringPermissions(permissions);
        return info;
    }

    private DnUser getUser(String account) {
        DnUser dnUser = dnAuthSerice.findUserByAccount(account);
        if (null == dnUser) {
            // 演示环境，即自动创建用户信息，自动授权
            dnUser = new DnUser();
            dnUser.setAccount(account);
            dnUser.setRealName("胡桃夹子");
            dnUser.setPassword("123456");
            boolean ret = dnAuthSerice.addUser(dnUser);
            if (ret) {
                String roleCode = DnConstants.ROLE_FOR_ADMIN;
                DnRole dnRole = dnAuthSerice.findRoleByRoleCode(roleCode);
                dnUser = dnAuthSerice.findUserByAccount(account);
                dnAuthSerice.authorizing(dnUser.getId(), dnRole.getId());
            }
        }

        return dnUser;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码<br>
     * 获取即将需要认证的信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        try {
            if (log.isDebugEnabled()) {
                log.debug("## 正在验证用户登录...");
            }

            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            String username = token.getUsername();

            if (StringUtils.isBlank(username)) {
                log.error("## 非法登录 .");
                throw new DnBusinessException(10, "非法用户身份");
            }

            DnUser dnUser = getUser(username);
            if (null == dnUser) {
                log.error("## 用户不存在={} .", username);
                throw new DnBusinessException(10, "账号或密码错误");
            }

            byte[] salt = Encodes.decodeHex(dnUser.getSalt());

            DnPrincipal principal = new DnPrincipal();
            principal.setDnUser(dnUser);
            //principal.setRoles(roleService.findRoleByUserId(user.getId()));

            //SecurityUtils.getSubject().getSession().setAttribute(Constants.PERMISSION_SESSION, permissionService.getPermissions(user.getId()));

            return new SimpleAuthenticationInfo(principal, dnUser.getPassword(), ByteSource.Util.bytes(salt), getName());
        } catch (AuthenticationException e) {
            log.error("# doGetAuthenticationInfo error , message={}", e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
