package org.opendolphin.jee.server;

import java.io.IOException;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
    }    

}
