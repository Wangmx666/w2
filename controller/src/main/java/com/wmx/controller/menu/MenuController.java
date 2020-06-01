package com.wmx.controller.menu;

import com.wmx.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    //跳转菜单树页面
    @RequestMapping("menu/toList")
    String toList() {
        return "menu/list";
    }
}
