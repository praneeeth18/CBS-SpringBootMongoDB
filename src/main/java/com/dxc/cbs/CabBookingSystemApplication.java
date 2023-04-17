package com.dxc.cbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import com.dxc.cbs.filter.UserFilter;

@SpringBootApplication
public class CabBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabBookingSystemApplication.class, args);
	}
	
	public FilterRegistrationBean userFilter() {
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setFilter(new UserFilter());
		filter.addUrlPatterns("/app/*");
		return filter;
	}

}
