package com.mycompany.servlet;

import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.server.adapter.DolphinServlet;
import com.mycompany.ApplicationDirector;

import javax.servlet.annotation.WebServlet;

/**
 * For real server mode, this servlet acts as entry point for all communication.
 */
@WebServlet("/dolphin/")
public class ApplicationServlet extends DolphinServlet{
    @Override
    protected void registerApplicationActions(ServerDolphin serverDolphin) {
        serverDolphin.register(new ApplicationDirector());
    }
}
