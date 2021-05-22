package com.dynamicform.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.dynamicform.app" })
public class DynamicFormApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DynamicFormApiApplication.class, args);
	}

}
