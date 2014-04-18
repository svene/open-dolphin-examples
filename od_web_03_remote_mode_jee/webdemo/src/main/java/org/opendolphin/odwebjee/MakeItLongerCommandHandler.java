package org.opendolphin.odwebjee;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.ServerAttribute;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.jee.server.CommandEvent;
import org.opendolphin.jee.server.CommandHandler;
import org.opendolphin.jee.server.ICommandHandler;
import org.opendolphin.jee.server.ModelStoreHolder;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Simple Command Handler to demonstrate how a PM-attribute is modified and the change is
 * immediately visible on the client.
 */
@Singleton
@CommandHandler(MakeItLongerCommandHandler.CMD_MAKE_IT_LONGER)
@SuppressWarnings("unused")
public class MakeItLongerCommandHandler implements ICommandHandler {

	public static final String CMD_MAKE_IT_LONGER= "makeItLonger";

	@Inject
	ModelStoreHolder modelStoreHolder;

    @Inject
    SomeEjb someEjb;

	@Override
	public void handleCommand(CommandEvent commandEvent) {

		// Get a handle to the PM which was initially created by the client (see index.jsp):
		PresentationModel pm = modelStoreHolder.getModelStore().findPresentationModelById(Constants.MY_PM);

		// get the PM's attribute 'myAttribute':
		final Attribute at = pm.getAt(Constants.MY_ATTRIBUTE);

		// Change the value of the attribute. change is immediately visible on the client:
		final String oldValue = (String)at.getValue();
		String newValue = someEjb.makeItLonger(oldValue);
		ServerDolphin.changeValue(commandEvent.getResponse(), (ServerAttribute) at, newValue);
	}
}
