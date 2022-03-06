package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.ArticleList;
import com.demo.entity.ArticleList;
import com.demo.mapper.ArticleListMapper;
import com.demo.service.ArticleListService;
import com.demo.util.BaseModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ArticleListServiceImpl implements ArticleListService {

	@Autowired
	private ArticleListMapper categoryMapper;

	@Override
	public BaseModel getByPage(Integer uid, int page, int pageSize) {
		BaseModel baseModel = new BaseModel();
		PageHelper.startPage(page, pageSize);
		List<ArticleList> c = categoryMapper.getByPage(uid);
		PageInfo<ArticleList> pageInfo = new PageInfo<ArticleList>(c,pageSize);
        baseModel.setResultCode(0);
        baseModel.setMessage("success");
        baseModel.setData(pageInfo);
		return baseModel;
	}

	@Override
	public ArticleList searchById(int id) {
		return categoryMapper.searchById(id);
	}

	@Override
	public int insert(ArticleList c) {
		return categoryMapper.insert(c);
	}

	@Override
	public int updateById(ArticleList c) {
		return categoryMapper.updateById(c);
	}

	@Override
	public int delete(Integer id) {
		return categoryMapper.delete(id);
	}


}
