package com.cj.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.data.rest.webmvc.RepositoryRestExporterServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.cj.config.security.SecurityConfig;

public class DataRestAndSecurityFilterInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		Dynamic dynamic= servletContext.addServlet("repositoryRest", new RepositoryRestExporterServlet());
		dynamic.addMapping("/api/*");
		
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy(
				SecurityConfig.SHIRO_FILTER_NAME);
		//servletContext.addFilter("dataRestFilter", delegatingFilterProxy).addMappingForUrlPatterns(null, false, "/*");
	}



}
