package com.demo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Users;
import com.demo.mapper.UsersDao;
import com.demo.service.UsersService;
import com.demo.util.BaseModel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersDao usersDao;

	@Override
	public BaseModel getByPageUser(int page, int limit,BaseModel baseModel) {
		// TODO Auto-generated method stub
		Page<Object> p = PageHelper.startPage(page,limit);
        // 开启分页以后需要马上接入查询语句，方便pageHelper实现分页
        List<Users> users=usersDao.getByPageUser(page,limit);
        PageInfo<Users> pageInfo = new PageInfo<Users>(users);
        baseModel.setResultCode(0);
        baseModel.setMessage("success");
        baseModel.setData(pageInfo);
        pageInfo.setTotal(p.getTotal());
		return baseModel;
	}

	@Override
	public Users login(Users u) {
		// TODO Auto-generated method stub
		return usersDao.login(u);
	}

	@Override
	public Integer insertSelective(Users user) {
		// TODO Auto-generated method stub
		return usersDao.insertSelective(user);
	}

	@Override
	public Integer getByAccount(String account) {
		// TODO Auto-generated method stub
		return usersDao.getByAccount(account);
	}

	@Override
	public Integer updateUser(Users user) {
		// TODO Auto-generated method stub
		return usersDao.updateUser(user);
	}

	@Override
	public Integer deleteUser(Integer id) {	
		return usersDao.deleteUser(id);
	}


}
