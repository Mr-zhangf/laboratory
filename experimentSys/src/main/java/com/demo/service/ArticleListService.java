package com.demo.service;

import com.demo.entity.ArticleList;
import com.demo.util.BaseModel;

public interface ArticleListService {
	
	BaseModel getByPage(Integer id,int page,int pageSize);
	ArticleList searchById(int id);
	int insert(ArticleList c);
	int updateById(ArticleList c);
	int delete(Integer id);

}
