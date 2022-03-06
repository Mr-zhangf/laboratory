package com.demo.mapper;

import java.util.List;
 
import com.demo.entity.Picture;

public interface PictureMapper {
	List<Picture> getByPage();
	Picture searchById(int id);
	int insert(Picture c);
	int updateById(Picture c);
	int delete(Integer id);

}