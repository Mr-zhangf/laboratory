package com.demo.mapper;

import java.util.List;

import com.demo.entity.News;

public interface NewsMapper {
	List<News> getByPage();
	News searchById(int id);
	int insert(News c);
	int updateById(News c);
	int delete(Integer id);
}