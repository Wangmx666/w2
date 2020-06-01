package com.wmx.service.role.impl;

import com.wmx.mapper.role.RoleMapper;
import com.wmx.model.role.Role;
import com.wmx.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public Set<String> selectRoleList(Role role) {
        //查询当前用户所拥有的角色
        Set<String> roleSet = roleMapper.selectRoleList(role);
        return roleSet;
    }
}
