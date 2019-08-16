package com.dubbo.nacos.consumer.controller.auth;

import com.dubbo.nacos.api.entity.auth.DnUser;
import com.dubbo.nacos.consumer.controller.DnBaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * authenticate relates controller method
 *
 * @author 胡桃夹子
 * @date 2019-08-05 22:36
 */
@Slf4j
public class DnAuthController extends DnBaseController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    String login(Model model) {
        model.addAttribute("user", new DnUser());
        log.info("#去登录");
        return "view/login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("userForm") DnUser dnUser, RedirectAttributes redirectAttributes) {
        log.info("# 登录中 ");
        if (null == dnUser || StringUtils.isBlank(dnUser.getAccount()) || StringUtils.isBlank(dnUser.getPassword())) {
            log.error("# 账号或密码错误");
            return "login";
        }

        String username = dnUser.getAccount();
        UsernamePasswordToken token = new UsernamePasswordToken(dnUser.getAccount(), dnUser.getPassword());
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            log.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            log.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            log.error("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            log.error("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException lae) {
            log.error("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.error("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.error("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        // 验证是否登录成功
        if (currentUser.isAuthenticated()) {
            log.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            return "redirect:/index";
        } else {
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "view/login/login";
    }


}
