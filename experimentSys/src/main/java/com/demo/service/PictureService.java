package com.demo.service;

import com.demo.entity.Picture;
import com.demo.util.BaseModel;

public interface PictureService {
	
	BaseModel getByPage( int page,int pageSize);
	Picture searchById(int id);
	int insert(Picture c);
	int updateById(Picture c);
	int delete(Integer id);

}
