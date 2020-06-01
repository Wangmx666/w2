package com.wmx.mapper.menu;

import com.wmx.model.menu.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

@Mapper
public interface MenuMapper {

    //查询左侧菜单树（动态）
    @Select("select t_id id,t_text name," +
            " t_href href, t_pid pid," +
            " t_type type" +
            " from t_menus" +
            " where t_pid = #{pid}")
    List<Menu> selectIndexMenuList(Menu menu);

    //查询权限（动态）
    @Select(" select m1.t_href" +
            " from t_u_r_mids urmid" +
            " join t_r_m_mids rmmid" +
            " on urmid.t_role_id = rmmid.t_role_id" +
            " join t_menus m1" +
            " on rmmid.t_menu_id = m1.t_id" +
            " where urmid.t_user_id = 1" +
            " and m1.t_href is not null" +
            " and m1.t_href != ''")
    Set<String> selectQuanxian(Menu menu);
}
