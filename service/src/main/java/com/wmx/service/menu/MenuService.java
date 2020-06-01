package com.wmx.service.menu;

import com.wmx.model.menu.Menu;

import java.util.List;
import java.util.Set;

public interface MenuService {

    //查询左侧菜单树（动态）
    List<Menu> selectIndexMenuList(Menu menu);

    //查询权限
    Set<String> selectQuanxian(Menu menu);
}
