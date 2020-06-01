package com.wmx.shiro;

import com.wmx.model.menu.Menu;
import com.wmx.model.role.Role;
import com.wmx.model.user.User;
import com.wmx.service.menu.MenuService;
import com.wmx.service.role.RoleService;
import com.wmx.service.user.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Set;

public class RealmConfig extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //用于授权（把用户的权限告诉shro）
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //手动给用户授予角色
//        Set<String> roleSet = new HashSet<>();
//        roleSet.add("admin");
//        roleSet.add("normal");

        //从数据库查询出 当前用户拥有的角色信息列表（ID和名字）
        Role role = new Role();
        //获取当前登录的用户信息
        User user = (User)principalCollection.getPrimaryPrincipal();
        role.setUserID(user.getUserID());
        Set<String> roleSet = roleService.selectRoleList(role);
        authorizationInfo.setRoles(roleSet);

        //手动给用户授予权限
//        Set<String> permSet = new HashSet<>();
//        permSet.add("book/deletes");

        Menu menu = new Menu();
        menu.setUserID(user.getUserID());
        Set<String> quanxian  = menuService.selectQuanxian(menu);
         authorizationInfo.setStringPermissions(quanxian);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //认证（用户登录）
        String userName = (String) authenticationToken.getPrincipal();//用户名
        String userPwd = new String((char[]) authenticationToken.getCredentials());//密码
        User u = new User();
        u.setUserAccount(userName);
        u.setUserPwd(userPwd);
        Map<String, Object> map = userService.login(u);
        if (map.get("code").toString().equals("1")) {
            User user = (User) map.get("user");
            //登录成功
            return new SimpleAuthenticationInfo(
                    // 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                    user,
                    //密码
                    user.getUserPwd(),
                    //salt = username + salt
                    ByteSource.Util.bytes(user.getUserAccount()),
                    //realm name
                    getName()
            );
        } else {
            return null;
        }
    }
}
