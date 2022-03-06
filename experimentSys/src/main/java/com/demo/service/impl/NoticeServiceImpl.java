package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Notice;
import com.demo.entity.Notice;
import com.demo.mapper.NoticeMapper;
import com.demo.service.NoticeService;
import com.demo.util.BaseModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper categoryMapper;

	@Override
	public BaseModel getByPage(String title,int page, int pageSize) {
		BaseModel baseModel = new BaseModel();
		PageHelper.startPage(page, pageSize);
		List<Notice> c = categoryMapper.getByPage(title);
		PageInfo<Notice> pageInfo = new PageInfo<Notice>(c,pageSize);
        baseModel.setResultCode(0);
        baseModel.setMessage("success");
        baseModel.setData(pageInfo);
		return baseModel;
	}

	@Override
	public Notice searchById(int id) {
		return categoryMapper.searchById(id);
	}

	@Override
	public int insert(Notice c) {
		return categoryMapper.insert(c);
	}

	@Override
	public int updateById(Notice c) {
		return categoryMapper.updateById(c);
	}

	@Override
	public int delete(Integer id) {
		return categoryMapper.delete(id);
	}


}
