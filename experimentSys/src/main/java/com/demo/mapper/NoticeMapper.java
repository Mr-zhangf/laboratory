package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.demo.entity.Notice;

public interface NoticeMapper {
	List<Notice> getByPage(@Param("title")String title);
	Notice searchById(int id);
	int insert(Notice c);
	int updateById(Notice c);
	int delete(Integer id);

}