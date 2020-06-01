package com.wmx.interceptor;



import com.wmx.model.menu.Menu;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class LoginIntercept extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag = false;
        //从session中取出userInfo，判断用户是否登录
        HttpSession session = httpServletRequest.getSession();
        Map<String, Object> userInfo = (Map<String, Object>)session.getAttribute("userInfo");
        if(null != userInfo && userInfo.get("code").toString().equals("1")) {
            //获取到当前请求的地址信息
            String requestURI = httpServletRequest.getRequestURI();
            //访问主页面时候不拦
            if(requestURI.endsWith("/index") || requestURI.endsWith("/toNoPermision")) {
                return true;
            }
            //获取session中存储的首页菜单地址
            Object menuList = session.getAttribute("menuList");
            if(null != menuList ) {
                List<Menu> list = (List<Menu>)menuList;
                for (Menu m : list) {
                    //取出1级菜单的2级节点
                   List<Menu> m2 = m.getChildren();
                    for (Menu erjicaidan : m2) {
                        if(null != erjicaidan.getHref() && requestURI.endsWith(erjicaidan.getHref())){
                            //该用户拥有此地址权限
                            return true;
                        }
                    }
                }
            }
            //该用户没有此权限,跳转无权限页面
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/toNoPermision");
            return false;
        } else {
            //未登录
            //重定向用户到登录页面
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/toLogin");
        }
        return flag;
    }

}
