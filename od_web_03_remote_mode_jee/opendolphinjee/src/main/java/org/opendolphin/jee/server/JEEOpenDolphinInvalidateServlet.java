package org.opendolphin.jee.server;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Invalidates HttpSession if requested by web client (useful for page reloads during development)
 */
@WebServlet(urlPatterns = {"/dolphininvalidate"})
public class JEEOpenDolphinInvalidateServlet extends HttpServlet {

	private final static Logger LOGGER = Logger.getLogger(JEEOpenDolphinInvalidateServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.warning("invalidating session: " + req.getSession().getId());
		req.getSession().invalidate();
		resp.setStatus(200);
		resp.getWriter().write("new session");
		resp.getWriter().flush();
		resp.getWriter().close();
    }

}
