package com.demo.service;

import com.demo.entity.Video;
import com.demo.util.BaseModel;

public interface VideoService {
	
	BaseModel getByPage(String title,int page,int pageSize);
	Video searchById(int id);
	int insert(Video c);
	int updateById(Video c);
	int delete(Integer id);

}
