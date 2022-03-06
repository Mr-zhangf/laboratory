package com.demo.service;

import com.demo.entity.Notice;
import com.demo.util.BaseModel;

public interface NoticeService {
	
	BaseModel getByPage(String title,int page,int pageSize);
	Notice searchById(int id);
	int insert(Notice c);
	int updateById(Notice c);
	int delete(Integer id);

}
