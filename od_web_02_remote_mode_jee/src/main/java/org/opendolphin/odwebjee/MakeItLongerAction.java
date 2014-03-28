package com.airhacks.jee1;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.ServerAttribute;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.SimpleCommandHandler;

/**
 *
 * @author sven
 */
public class MakeItLongerAction extends DolphinServerAction {

    @Override
    public void registerIn(ActionRegistry ar) {
        ar.register("makeItLonger", new SimpleCommandHandler() {
 
            @Override
            public void handleCommand() {
                
                ServerPresentationModel pm = getServerDolphin().getAt("myPM");
                final ServerAttribute at = pm.getAt("myAttribute");
                changeValue(at, at.getValue() + " bla");
            }
        });

    }
}
