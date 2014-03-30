package org.opendolphin.odwebjee;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.server.adapter.DolphinServlet;

@WebServlet(urlPatterns = {"/dolphin"})
public class MyDolphinServlet extends DolphinServlet {

    @Inject
    MakeItLongerAction makeItLongerAction;
    
    @Override
    protected void registerApplicationActions(final ServerDolphin serverDolphin) {
        serverDolphin.register(makeItLongerAction);
    }

}
