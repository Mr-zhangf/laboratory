package com.demo.service;

import com.demo.entity.News;
import com.demo.util.BaseModel;

public interface NewsService {
	
	BaseModel getByPage( int page,int pageSize);
	News searchById(int id);
	int insert(News c);
	int updateById(News c);
	int delete(Integer id);

}
