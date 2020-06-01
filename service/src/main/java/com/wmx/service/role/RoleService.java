package com.wmx.service.role;

import com.wmx.model.role.Role;

import java.util.Set;

public interface RoleService {

    //查询当前用户所拥有的角色
    Set<String> selectRoleList(Role role);
}
