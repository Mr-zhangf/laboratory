package com.demo.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	public UploadFile(){
		
	}
	public String uploadImg(MultipartFile file, HttpServletRequest request){
		String prefix="";
        String dateStr="";
		try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                dateStr = String.valueOf(new Date().getTime());
                //String filepath = request.getServletContext().getRealPath("/upload/") + dateStr + "." + prefix;
                String filepath = "D:/upload/" + dateStr + "." + prefix;
                filepath = filepath.replace("\\", "/");
                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        }catch (Exception e){
        }
		return "/upload/" + dateStr + "." + prefix;
	}
	
	public String uploadImg(MultipartFile file){
		String originalName = file.getOriginalFilename();
        String prefix=originalName.substring(originalName.lastIndexOf(".")+1);
        String dateStr = String.valueOf(new Date().getTime());
        //String filepath = request.getServletContext().getRealPath("/static") + uploadDir + dateStr + "." + prefix;
        String filepath = "D:/upload/" + dateStr + "." + prefix;
        filepath = filepath.replace("\\", "/");
        File files=new File(filepath);
        //打印查看上传路径
        System.out.println(filepath);
        if(!files.getParentFile().exists()){
            files.getParentFile().mkdirs();
        }
        try {
			file.transferTo(files);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "upload/" + dateStr + "." + prefix;
	}
}
