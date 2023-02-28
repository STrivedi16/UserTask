package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import ch.qos.logback.core.Context;

@Service
public class TymeleafService {

	 @Autowired
	    private TemplateEngine templateEngine;

	    
	    public String generateHtml() {
	        org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
	        context.setVariable("message", "Hello Thymeleaf!");
	        String html = templateEngine.process("my-template", context);
	        return html;
	    }
	}
	//In the above example, my-template.html is a Thymeleaf template located in the src/main/resources/templates/ directory. The Context object contains variables that can be used in the template, and the process() method generates the HTML content using the specified template and context.






