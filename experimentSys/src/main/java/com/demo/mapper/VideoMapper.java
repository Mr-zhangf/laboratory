package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.demo.entity.Video;

public interface VideoMapper {
	List<Video> getByPage(@Param("title")String title);
	Video searchById(int id);
	int insert(Video c);
	int updateById(Video c);
	int delete(Integer id);

}