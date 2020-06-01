package com.wmx.controller.index;

import com.wmx.model.menu.Menu;
import com.wmx.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private MenuService menuService;


    //查询左侧菜单树（动态）
    @RequestMapping("index")
    String toIndex (Menu menu, ModelMap mm){
        //查询左侧菜单树
        List<Menu> list = menuService.selectIndexMenuList(menu);
        mm.addAttribute("list",list);
        return "index";
    }

    //无权限
    @RequestMapping("noAuth")
    String toNoAuth() {
        return "noAuth";
    }


}
