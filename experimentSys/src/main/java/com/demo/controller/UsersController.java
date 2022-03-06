package com.demo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.Users;
import com.demo.service.UsersService;
import com.demo.util.BaseModel;

@RequestMapping("/users")
@Controller
public class UsersController {

    final UsersService usersService;

	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	/**
     * 查询
     * @param page
     * @param limit
     * @param baseModel
     * @return
     */
    @RequestMapping(value = "/getByPageUser", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel getByPageUser(int page, int limit, BaseModel baseModel){
        return usersService.getByPageUser(page,limit,baseModel);
    }
    
    /**
     * 登录
     * @param user
     * @param baseModel
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel selectPageUser(Users user, BaseModel baseModel, HttpSession session) throws Exception{
    	
    	Users u = usersService.login(user);
    	if(u != null){
    		session.setAttribute("admin", u);
    		baseModel.setResultCode(200);
    		baseModel.setMessage("登录成功！");
    		baseModel.setData(u);
    	}else{
    		baseModel.setResultCode(500);
    		baseModel.setMessage("账号或密码错误！");
    	}	
    	return baseModel;
    }
    
    /**
     * 退出
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public void loginOut(HttpServletRequest request) throws Exception{
    	HttpSession session = request.getSession();
    	session.setAttribute("userInfo", null);
    }

    /**
     * 修改
     * @param user
     * @param birthdaystr
     * @param baseModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel updateUser(Users user,String birthdaystr,BaseModel baseModel) throws Exception{
 
    	Integer result = usersService.updateUser(user);
    	if(result > 0){
    		baseModel.setResultCode(200);
    		baseModel.setMessage("修改成功！");
    	}else{
    		baseModel.setResultCode(500);
    		baseModel.setMessage("添加失败！");
    	}
    	return baseModel;
    }
    
    /**
     * 获取用户信息
     * @param user
     * @param baseModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel getUserInfo(Users user,BaseModel baseModel) throws Exception{
    	
    	Users u = usersService.login(user);
    	if(u  != null){
    		baseModel.setData(u);
    		baseModel.setResultCode(200);
    		baseModel.setMessage("success");
    	}else{
    		baseModel.setResultCode(500);
    		baseModel.setMessage("error");
    	}
    	return baseModel;
    }
    
    /**
     * 添加
     * @param user
     * @param birthdaystr
     * @param baseModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel addUser(Users user,String birthdaystr,BaseModel baseModel) throws Exception{
    	
    	Integer result = usersService.insertSelective(user);
    	if(result > 0){
    		baseModel.setResultCode(200);
    		baseModel.setMessage("添加成功！");
    	}else{
    		baseModel.setResultCode(500);
    		baseModel.setMessage("添加失败！");
    	}
    	return baseModel;
    }
    
    /**
     * 删除
     * @param uid
     * @param baseModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel deleteUser(Integer uid,BaseModel baseModel)throws Exception{
    	
    	Integer result = usersService.deleteUser(uid);
    	if(result > 0){
    		baseModel.setResultCode(200);
    		baseModel.setMessage("删除成功！");
    	}else{
    		baseModel.setResultCode(500);
    		baseModel.setMessage("删除失败！");
    	}
    	return baseModel;
    }
}

