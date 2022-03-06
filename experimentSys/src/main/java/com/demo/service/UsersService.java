package com.demo.service;

import com.demo.entity.Users;
import com.demo.util.BaseModel;

public interface UsersService {
   
	public BaseModel getByPageUser(int page, int limit,BaseModel baseModel);
    Users login(Users u);
    Integer insertSelective(Users user);
    Integer getByAccount(String account);
    Integer updateUser(Users user);
    Integer deleteUser(Integer id);
}
