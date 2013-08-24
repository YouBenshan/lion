package com.cj.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;

/**
 * loads the <A href="http://127.0.0.1:8080/{projectName}/h2/">web-based H2 database
 * console</A>.
 * <p/>
 * To access the database for this application, use the JDBC URI {@code
 * jdbc:h2:mem:$ app.properties.database.name}}.
 */
public class H2EmbeddedDatbaseConsoleInitializer implements
		WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		WebServlet webServlet = new WebServlet();
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("h2",
				webServlet);
		dynamic.setInitParameter("trace", "true");
		dynamic.setAsyncSupported(true);
		dynamic.addMapping("/h2/*");
		dynamic.setLoadOnStartup(1);
	}
}
