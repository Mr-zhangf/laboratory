package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Article;
import com.demo.mapper.ArticleMapper;
import com.demo.service.ArticleService;
import com.demo.util.BaseModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper addressMapper;

	@Override
	public BaseModel getByPage(Integer uid,int page, int pageSize) {
		BaseModel baseModel = new BaseModel();
		PageHelper.startPage(page, pageSize);
		List<Article> c = addressMapper.getByPage(uid);
		PageInfo<Article> pageInfo = new PageInfo<Article>(c,pageSize);
		//pageInfo.setTotal(categoryMapper.findCategoryCount(entity));
        baseModel.setResultCode(0);
        baseModel.setMessage("success");
        baseModel.setData(pageInfo);
		return baseModel;
	}

	@Override
	public Article searchById(int id) {
		return addressMapper.searchById(id);
	}

	@Override
	public int insert(Article c) {
		return addressMapper.insert(c);
	}

	@Override
	public int updateById(Article c) {
		return addressMapper.updateById(c);
	}

	@Override
	public int delete(Integer id) {
		return addressMapper.delete(id);
	}

}
