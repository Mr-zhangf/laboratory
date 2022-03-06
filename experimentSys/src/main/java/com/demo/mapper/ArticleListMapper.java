package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.demo.entity.ArticleList;

public interface ArticleListMapper {
	List<ArticleList> getByPage(@Param("type")Integer type);
	ArticleList searchById(int id);
	int insert(ArticleList c);
	int updateById(ArticleList c);
	int delete(Integer id);

}