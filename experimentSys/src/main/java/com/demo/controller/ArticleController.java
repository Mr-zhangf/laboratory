package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entity.Article;
import com.demo.service.ArticleService;
import com.demo.util.BaseModel;

/**
 * @author Mr.张
 */
@RestController

@RequestMapping("/article")
public class ArticleController {
	
	private final ArticleService initService;

	public ArticleController(ArticleService initService) {
		this.initService = initService;
	}


	/**
     * 修改
     * @return
     * @throws IOException 
     * @throws IllegalStateException 
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel edit(Article news) {
		BaseModel baseModel = new BaseModel();
        initService.updateById(news);
		baseModel.setResultCode(200);
		baseModel.setMessage("保存成功！");
		return baseModel;
	}

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	public BaseModel deleteinit(Integer id) {
		BaseModel baseModel = new BaseModel();
		initService.delete(id);
		baseModel.setResultCode(200);
		baseModel.setMessage("success");
		return baseModel;
	}
	
    
    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
	public BaseModel getById(int id) {
		BaseModel baseModel = new BaseModel();
		Article init = initService.searchById(id);
		baseModel.setResultCode(200);
		baseModel.setData(init);
		baseModel.setMessage("success");
		return baseModel;
	}
	
    /**
     * 获取列表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
	public BaseModel findinit(Integer type,int page,int pageSize) {
		BaseModel baseModel = new BaseModel();
		baseModel = initService.getByPage(type,page,pageSize);
		return baseModel;
	}
	
}
