package com.wmx.mapper.user;

import com.wmx.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    //查询账号密码（登录）
    @Select("select t_id userID," +
            " t_account userAccount," +
            " t_pwd userPwd" +
            " from t_users" +
            " where t_account = #{userAccount}" +
            " and t_pwd = #{userPwd}")
    User login(User user);

    //查询所有用户
    @Select(" select t_id userID," +
            " t_account userAccount," +
            " t_pwd userPwd" +
            " from t_users")
    List<User> selectUserList(User user);
}
