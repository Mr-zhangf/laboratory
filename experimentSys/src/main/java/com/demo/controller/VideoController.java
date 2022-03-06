package com.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.entity.Video;
import com.demo.service.VideoService;
import com.demo.util.BaseModel;

/**
 * @author Mr.张
 */
@RestController

@RequestMapping("/video")
public class VideoController {
	
	private final VideoService initService;

	public VideoController(VideoService initService) {
		this.initService = initService;
	}


	/**
	 * 添加
	 * @param init
	 * @param file
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel add(Video init ) {
		BaseModel baseModel = new BaseModel();
		 
		initService.insert(init);
		baseModel.setResultCode(200);
		baseModel.setMessage("success");
		return baseModel;
	}
	
    /**
     * 修改
     * @param Video
     * @return
     * @throws IOException 
     * @throws IllegalStateException 
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel success(Video init ) throws IllegalStateException, IOException {
		BaseModel baseModel = new BaseModel();
		 
		initService.updateById(init);
		baseModel.setResultCode(200);
		baseModel.setMessage("success");
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
		Video init = initService.searchById(id);
		baseModel.setResultCode(200);
		baseModel.setData(init);
		baseModel.setMessage("success");
		return baseModel;
	}
	
    /**
     * 获取列表
     * @param uid
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
	public BaseModel findinit(String title,int page,int pageSize) {
		BaseModel baseModel = new BaseModel();
		baseModel = initService.getByPage(title,page,pageSize);
		return baseModel;
	}
	
}
