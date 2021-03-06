<%@page language="java" contentType="text/javascript; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.ApplicationConstants" %>

// private
// get values from server as javascript values, so that they can be used in client side javascript:
readDolphinConfig = function() {
	return {
		DOLPHIN_URL: "<%=application.getContextPath()%>/dolphin/",
		ODAPI: {
			PM_ID: "<%=ApplicationConstants.PM_APP%>",
			ATT_NAME: "<%=ApplicationConstants.ATT_NAME%>",
			ATT_GREETING: "<%=ApplicationConstants.ATT_GREETING%>",
            COMMAND_INIT: "<%=ApplicationConstants.COMMAND_INIT%>",
			COMMAND_GREET: "<%=ApplicationConstants.COMMAND_GREET%>",
		}
	}
};

