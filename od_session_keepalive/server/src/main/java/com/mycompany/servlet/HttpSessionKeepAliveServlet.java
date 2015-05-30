package com.mycompany.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/pingsession")
public class HttpSessionKeepAliveServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("** HttpSessionKeepAliveServlet.doGet: keeping session alive: " + new Date());
		req.getSession(false); // no need to create a new session if there is none since we only want to keep the existing session alive
	}
}
