package com.mycompany;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationServletContextListener implements ServletContextListener {


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();

		// Configure CORS Filter:
		FilterRegistration.Dynamic corsFilter = ctx.addFilter("corsFilter", CorsFilter.class);
		corsFilter.addMappingForUrlPatterns(null, true, "/*");
		corsFilter.getInitParameters().put("origin-whitelist", "http://localhost:63342,null");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
