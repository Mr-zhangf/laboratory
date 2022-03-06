package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.entity.Article;

public interface ArticleMapper {
	List<Article> getByPage(@Param("type")Integer type);
	Article searchById(int id);
	int insert(Article c);
	int updateById(Article c);
	int delete(Integer id);
}