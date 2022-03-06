package com.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.util.BaseModel;



/**
 * @author Mr.张
 */
@RestController
@RequestMapping("/file")
public class FileController{

	/**
     * 文件上传功能
     * @param file
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST,consumes = "multipart/*",headers = "content-type=multipart/form-data")
    public BaseModel upload(MultipartFile file,HttpServletRequest request){
    	BaseModel baseModel = new BaseModel();
    	try {
			System.out.println("文件上传");
			String path = request.getSession().getServletContext().getRealPath("upload");
			String originalFileName = file.getOriginalFilename();//获取原始图片的扩展名
			System.out.println("图片原始名称："+originalFileName);
			String newFileName = UUID.randomUUID()+originalFileName;  //新的文件名称
			System.out.println("新的文件名称："+newFileName);
			File targetFile = new File(path,newFileName); //创建新文件        
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			//MultipartFile自带的解析方法
			file.transferTo(targetFile);
			baseModel.setData(newFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return baseModel;
    }
	
    /**
     * 文件上传
     * @return {Result}
     */
    @RequestMapping(value = "/savefile", method = {RequestMethod.POST})
    @ResponseBody
    public Object headImg(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String prefix="";
        String dateStr="";
        String uploadDir = "/upload/";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                dateStr = String.valueOf(new Date().getTime());
                //String filepath = request.getServletContext().getRealPath("/static") + uploadDir + dateStr + "." + prefix;
                String filepath = "D:/upload/" + dateStr + "." + prefix;
                filepath = filepath.replace("\\", "/");
                System.out.println( filepath+"*********************************");
                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map2=new HashMap<String,Object>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("data",map2);
        map2.put("src", uploadDir + dateStr + "." + prefix);
        return map;
    }
	
    
    @RequestMapping(value = "/fileUpload", method = {RequestMethod.POST})
    @ResponseBody
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D://temp-rainy//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        model.addAttribute("filename", filename);
        return "file";
    }
	
	@RequestMapping(value = "/getproductImg", method = RequestMethod.POST)
    public void download(@RequestParam("fileName")String fileName,HttpServletRequest request,HttpServletResponse response){
    	InputStream bis=null;
    	BufferedOutputStream out=null;
    	//OutputStream out=null;
    	try{
    		 //模拟文件，myfile.txt为需要下载的文件
            String name = request.getSession().getServletContext().getRealPath("upload")+"/"+fileName;
            //获取输入流
            bis= new BufferedInputStream(new FileInputStream(new File(name)));
            //bis=new FileInputStream(fileName);
            //假如以中文名下载的话
            String filename = name;
            //转码，免得文件名中文乱码
            filename = URLEncoder.encode(filename,"UTF-8");
            out = new BufferedOutputStream(response.getOutputStream());
            response.reset();
            //设置文件下载头
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);  
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
            response.setContentType("application/octet-stream"); 
            response.setContentLength(bis.available());
            System.out.println(bis.available());
            //out=response.getOutputStream();
            int len = 0;
            byte buffer[]=new byte[1024];
            while((len = bis.read(buffer))>0){
                out.write(buffer,0,len);
            }
            
            out.flush();
            out.close();
            bis.close();
    	}catch(Exception e){
    		System.out.println("下载异常");
    		e.printStackTrace();
    	}
    }
}
