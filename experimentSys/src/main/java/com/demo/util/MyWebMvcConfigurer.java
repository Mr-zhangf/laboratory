package com.demo.util;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Mr.张
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/upload/");
	}
	
	/**
    * 文件上传配置
    * @return
    */
   @Bean
   public MultipartConfigElement multipartConfigElement() {
      MultipartConfigFactory factory = new MultipartConfigFactory();
      //单个文件最大
      factory.setMaxFileSize("30000MB"); //KB,MB
      /// 设置总上传数据总大小
      factory.setMaxRequestSize("300000MB");
      return factory.createMultipartConfig();
   }
}
