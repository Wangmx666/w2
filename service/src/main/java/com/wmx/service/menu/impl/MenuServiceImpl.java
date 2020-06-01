package com.wmx.service.menu.impl;

import com.wmx.mapper.menu.MenuMapper;
import com.wmx.model.menu.Menu;
import com.wmx.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    //查询左侧菜单树（动态）
    @Override
    public List<Menu> selectIndexMenuList(Menu menu) {
        List<Menu> list =  menuMapper.selectIndexMenuList(menu);
        //判断查出的list是不是空的并且循环取出每个元素的ID
        if(null != list && 0 < list.size()) {
            for (Menu menu1 : list) {
                //new一个新的Menu实例
                Menu m = new Menu();
                //取出list里每个元素的ID 并赋予 Pid
                m.setPid(menu1.getId());
                //递归 ： 再次调用方法查询 以Pid作为条件查第二层菜单
                List<Menu> menu2 = selectIndexMenuList(m);
                //判断查出的第二层实例是不是空的
                if (null != menu2 && 0 < menu2.size()) {
                    //将查出的第二层实例赋予第一层 封装类list
                    menu1.setChildren(menu2);
                }
            }
        }
        return list;
    }

    //查询权限
    @Override
    public Set<String> selectQuanxian(Menu menu) {
        Set<String> quanxian  = menuMapper.selectQuanxian(menu);
        return quanxian;
    }
}
