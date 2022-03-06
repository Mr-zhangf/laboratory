package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.entity.Users;

public interface UsersDao {
    List<Users> getByPageUser(@Param("page") int page, @Param("limit") int limit);
    
    Long getTotal();
    
    Users login(Users u);
//    新增
    Integer insertSelective(Users user);
    //根据账号查询信息
    Integer getByAccount(String account);
//    修改用户信息
    Integer updateUser(Users user);
//    删除用户信息
    Integer deleteUser(Integer uid);
}