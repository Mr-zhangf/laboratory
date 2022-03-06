package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Picture;
import com.demo.entity.Picture;
import com.demo.mapper.PictureMapper;
import com.demo.service.PictureService;
import com.demo.util.BaseModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class PictureServiceImpl implements PictureService {

	@Autowired
	private PictureMapper categoryMapper;
	
	public BaseModel getByPage(int page, int pageSize) {
		BaseModel baseModel = new BaseModel();
		PageHelper.startPage(page, pageSize);
		List<Picture> c = categoryMapper.getByPage();
		PageInfo<Picture> pageInfo = new PageInfo<Picture>(c,pageSize);
        baseModel.setResultCode(0);
        baseModel.setMessage("success");
        baseModel.setData(pageInfo);
		return baseModel;
	}

	@Override
	public Picture searchById(int id) {
		return categoryMapper.searchById(id);
	}

	@Override
	public int insert(Picture c) {
		return categoryMapper.insert(c);
	}

	@Override
	public int updateById(Picture c) {
		return categoryMapper.updateById(c);
	}

	@Override
	public int delete(Integer id) {
		return categoryMapper.delete(id);
	}


}
