package com.demo.service;

import com.demo.entity.Article;
import com.demo.util.BaseModel;


public interface ArticleService {
	
	BaseModel getByPage(Integer id,int page,int pageSize);
	Article searchById(int id);
	int insert(Article c);
	int updateById(Article c);
	int delete(Integer id);

}
