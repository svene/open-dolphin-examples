package org.opendolphin.odwebjee;

import org.opendolphin.core.server.ServerAttribute;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.SimpleCommandHandler;

/**
 * Simple Action to demonstrate how a PM-attribute is modified and the
 * change is immediately visible on the client.
 */
public class MakeItLongerAction extends DolphinServerAction {

    @Override
    public void registerIn(ActionRegistry registry) {

        // Register CommandHandler under the name 'makeItLonger':
        registry.register("makeItLonger", new SimpleCommandHandler() {
 
            @Override
            public void handleCommand() {                
                // Get a handle to the PM which was initially created by the client (see index.html):
                ServerPresentationModel pm = getServerDolphin().getAt("myPM");
                
                // get the PM's attribute 'myAttribute':
                final ServerAttribute at = pm.getAt("myAttribute");
                
                // Change the value of the attribute. change is immediately visible on the client: 
                changeValue(at, at.getValue() + " longer");
            }
        });

    }
}
