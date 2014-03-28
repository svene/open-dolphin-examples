package org.opendolphin.odwebjee;

import javax.servlet.annotation.WebServlet;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.server.adapter.DolphinServlet;

// open at: http://localhost:8080/odwebjee/

@WebServlet(urlPatterns = {"/dolphin"})
public class MyDolphinServlet extends DolphinServlet {

    @Override
    protected void registerApplicationActions(final ServerDolphin serverDolphin) {
        serverDolphin.register(new MakeItLongerAction());
    }

}
