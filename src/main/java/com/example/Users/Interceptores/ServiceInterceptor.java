package com.example.Users.Interceptores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ServiceInterceptor extends WebMvcConfigurerAdapter {

	Logger LOG = LoggerFactory.getLogger(ServiceInterceptor.class);

	@Autowired
	private Interceptore interceptore;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LOG.info(" interceptor invoked....");

		System.out.println("this is in intreceptor");
		registry.addInterceptor(interceptore);
	}

}
