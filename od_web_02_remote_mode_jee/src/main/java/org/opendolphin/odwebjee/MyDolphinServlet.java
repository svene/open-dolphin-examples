/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.jee1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.SimpleCommandHandler;
import org.opendolphin.server.adapter.DolphinServlet;

// open at: http://localhost:8080/jeeapp1/


@WebServlet(urlPatterns = {"/dolphin"})
public class MyDolphinServlet extends DolphinServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void registerApplicationActions(final ServerDolphin serverDolphin) {
        
        serverDolphin.register(new MakeItLongerAction());
 
 
    }

}
