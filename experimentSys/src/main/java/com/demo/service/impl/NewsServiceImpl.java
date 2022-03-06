package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.News;
import com.demo.mapper.NewsMapper;
import com.demo.service.NewsService;
import com.demo.util.BaseModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;

	@Override
	public BaseModel getByPage(int page, int pageSize) {
		BaseModel baseModel = new BaseModel();
		PageHelper.startPage(page, pageSize);
		List<News> c = newsMapper.getByPage();
		PageInfo<News> pageInfo = new PageInfo<News>(c,pageSize);
        baseModel.setResultCode(0);
        baseModel.setMessage("success");
        baseModel.setData(pageInfo);
		return baseModel;
	}

	@Override
	public News searchById(int id) {
		return newsMapper.searchById(id);
	}

	@Override
	public int insert(News c) {
		return newsMapper.insert(c);
	}

	@Override
	public int updateById(News c) {
		return newsMapper.updateById(c);
	}

	@Override
	public int delete(Integer id) {
		return newsMapper.delete(id);
	}

}
