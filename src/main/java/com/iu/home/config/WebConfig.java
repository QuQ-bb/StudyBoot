package com.iu.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration //***-context.xml역할 
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${app.upload.base}")
	private String filePath;

	@Value("${app.url.path}")
	private String urlPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler(urlPath)
				.addResourceLocations(filePath);
	}
}
