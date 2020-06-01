package com.wmx.controller.user;

import com.wmx.common.ImageCode;
import com.wmx.model.user.User;
import com.wmx.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //跳转登录页面
    @RequestMapping("toLogin")
    String toLogin () {
        return "login";
    }

    //调用图片验证码方法
    @RequestMapping("imgcode")
    void imgCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImageCode.getCode3(request, response, request.getSession());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**登录功能，查询账号密码
    @RequestMapping("login")
    @ResponseBody
    Map<String, Object> login(HttpServletRequest request, User user) {
        Map<String, Object> map = new HashMap<>();
        //2代表验证码错误
        map.put("code",2);
        HttpSession session = request.getSession();
        //判断验证码，正确的验证码才能查询数据库
        //session中取出系统自动生成验证码
        Object imgCodeObj = session.getAttribute("imageCode");
        //判断是否空
        if (null != imgCodeObj) {
            //判断是否正确
            if(imgCodeObj.toString().equalsIgnoreCase(user.getUserImgCode())) {
                //执行登录
                map = userService.login(user);
                //把登录信息(查询的账号密码 和 code（是否存在）)放在session中
                session.setAttribute("userInfo",map);
            }
        }
        return map;
    }
*/
    @RequestMapping("login")
    @ResponseBody
    Map<String, Object> login(User user, HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        //默认验证码错误
        map.put("code",2);
        try {
            //得到session
            HttpSession session = request.getSession();
            //判断验证码，正确的验证码才能查询数据库
            //session中取出系统自动生成验证码
            Object imgCodeObj = session.getAttribute("imageCode");
            if (null != imgCodeObj) {
                //判断是否正确
                if(imgCodeObj.toString().equalsIgnoreCase(user.getUserImgCode())) {
                    //执行登录
                    //获取Subject实例对象,用户实例（相当于获取到了自定义的realm对象）+
                    Subject subject = SecurityUtils.getSubject();
                    //将用户名和密码封装到UsernamePasswordToken
                    UsernamePasswordToken token = new UsernamePasswordToken(user.getUserAccount(),user.getUserPwd());
                    //调用login()方法（相当于调用realm中的认证登录方法）
                    subject.login(token);
                    //判断是否登录成功
                    Object o = subject.getPrincipals().getPrimaryPrincipal();
                    if(null != o) {
                        //登录成功
                        map.put("code",1);
                    }
                }
            }
        } catch(AuthenticationException authenticationException) {
            map.put("code", 0);
        } finally {
            return map;
        }
    }


    //跳转list页面
    @RequestMapping("user/toList")
    String toUserList() {
        return "user/list";
    }

    //查询用户列表
    @RequestMapping("user/select")
    @ResponseBody
    Map<String, Object> selectUserList(User user) {
        Map<String, Object> map = userService.selectUserList(user);
        return map;
    }


}
