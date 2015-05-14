package com.mycompany;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CorsFilter implements Filter {

	private List<String> originWhitelist = new ArrayList<>();

	@Override
	public void init(FilterConfig config) throws ServletException {
		String originWhitelistString = config.getInitParameter("origin-whitelist");
		if (originWhitelist != null) {
			for (String entry : originWhitelistString.split(",")) {
				String s = entry.trim();
				originWhitelist.add(s);
			}
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String origin = request.getHeader("origin");
		if (originWhitelist.contains(origin)) {
			response.setHeader("Access-Control-Allow-Origin", origin); // Allow origin sent by request if whitelisted in web.xml
		}
		response.setHeader("Access-Control-Allow-Credentials", "true"); // Allow JSESSIONID cookie
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}
}
