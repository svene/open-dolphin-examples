package com.mycompany.servlet;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class AppSessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("===>>> AppSessionInitializer.sessionCreated");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("<<<== AppSessionInitializer.sessionDestroyed");
	}
}
