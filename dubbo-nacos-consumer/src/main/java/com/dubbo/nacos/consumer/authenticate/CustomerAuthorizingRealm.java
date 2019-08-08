package com.dubbo.nacos.consumer.authenticate;

import com.dubbo.nacos.api.entity.auth.User;
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
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
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
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("user:show");
        stringSet.add("user:admin");
        info.setStringPermissions(stringSet);
        return info;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码<br>
     * 获取即将需要认证的信息
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

            User user = dnAuthSerice.findUserByAccount(username);
            if (null == user) {
                log.error("## 用户不存在={} .", username);
                throw new DnBusinessException(10, "账号或密码错误");
            }

            byte[] salt = Encodes.decodeHex(user.getSalt());

            DnPrincipal principal = new DnPrincipal();
            principal.setUser(user);
            //principal.setRoles(roleService.findRoleByUserId(user.getId()));

            //SecurityUtils.getSubject().getSession().setAttribute(Constants.PERMISSION_SESSION, permissionService.getPermissions(user.getId()));

            return new SimpleAuthenticationInfo(principal, user.getPassword(), ByteSource.Util.bytes(salt), getName());
        } catch (AuthenticationException e) {
            log.error("# doGetAuthenticationInfo error , message={}", e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
