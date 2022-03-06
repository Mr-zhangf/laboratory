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

import com.demo.entity.News;
import com.demo.service.NewsService;
import com.demo.util.BaseModel;


/**
 * @author Mr.张
 */
@RestController

@RequestMapping("/news")
public class NewsController {
	
	private final NewsService newsService;

	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}


	/**
	 * 添加
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
    @RequestMapping(value = "/insertNews", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel insertNews(News news ) throws IllegalStateException, IOException {
		BaseModel baseModel = new BaseModel();
		newsService.insert(news);
		baseModel.setResultCode(200);
		baseModel.setMessage("添加成功！");
		return baseModel;
	}
	
	
    /**
     * 修改
     * @return
     * @throws IOException 
     * @throws IllegalStateException 
     */
    @RequestMapping(value = "/updateNews", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel updateNews(News news ) throws IllegalStateException, IOException {
		BaseModel baseModel = new BaseModel();
		 
		newsService.updateById(news);
		baseModel.setResultCode(200);
		baseModel.setMessage("修改成功！");
		return baseModel;
	}

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
	public BaseModel deleteNews(Integer id) {
		BaseModel baseModel = new BaseModel();
		newsService.delete(id);
		baseModel.setResultCode(200);
		baseModel.setMessage("删除成功！");
		return baseModel;
	}
	
    
    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
	public BaseModel getById(int id) {
		BaseModel baseModel = new BaseModel();
		News News = newsService.searchById(id);
		baseModel.setResultCode(200);
		baseModel.setData(News);
		baseModel.setMessage("查询成功！");
		return baseModel;
	}
	
    /**
     * 查询列表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findNews", method = RequestMethod.GET)
	public BaseModel findNews(int page,int pageSize) {
		BaseModel baseModel = new BaseModel();
		baseModel = newsService.getByPage(page,pageSize);
		return baseModel;
	}
	
}
