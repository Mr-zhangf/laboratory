package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Video;
import com.demo.entity.Video;
import com.demo.mapper.VideoMapper;
import com.demo.service.VideoService;
import com.demo.util.BaseModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoMapper categoryMapper;

	@Override
	public BaseModel getByPage(String title,int page, int pageSize) {
		BaseModel baseModel = new BaseModel();
		PageHelper.startPage(page, pageSize);
		List<Video> c = categoryMapper.getByPage(title);
		PageInfo<Video> pageInfo = new PageInfo<Video>(c,pageSize);
        baseModel.setResultCode(0);
        baseModel.setMessage("success");
        baseModel.setData(pageInfo);
		return baseModel;
	}

	@Override
	public Video searchById(int id) {
		return categoryMapper.searchById(id);
	}

	@Override
	public int insert(Video c) {
		return categoryMapper.insert(c);
	}

	@Override
	public int updateById(Video c) {
		return categoryMapper.updateById(c);
	}

	@Override
	public int delete(Integer id) {
		return categoryMapper.delete(id);
	}


}
