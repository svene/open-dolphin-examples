package com.mycompany;

import com.mycompany.servlet.ApplicationServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationServletContextListener implements ServletContextListener {


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();

		// Configure Dolphin Servlet:
		ServletRegistration.Dynamic dolphinServlet = ctx.addServlet("dolphinServlet", ApplicationServlet.class);
		dolphinServlet.addMapping("/dolphin/");

		// Configure corsFilter:
		FilterRegistration.Dynamic corsFilter = ctx.addFilter("corsFilter", CorsFilter.class);
		corsFilter.addMappingForUrlPatterns(null, true, "/*");
		corsFilter.getInitParameters().put("origin-whitelist", "http://localhost:63342,null");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
