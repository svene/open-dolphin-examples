package org.opendolphin.odwebjee.makeitlonger.dolphin;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.*;
import org.opendolphin.jee.server.CommandEvent;
import org.opendolphin.jee.server.DolphinCommandHandler;
import org.opendolphin.jee.server.DolphinSessionBeans;
import org.opendolphin.jee.server.ICommandHandler;
import org.opendolphin.odwebjee.makeitlonger.boundary.Appender;

import javax.inject.Inject;

/**
 * Simple Command Handler to demonstrate how a PM-attribute is modified and the change is
 * immediately visible on the client.
 */
@DolphinCommandHandler(InitCommandHandler.CMD_ID)
@SuppressWarnings("unused")
public class InitCommandHandler implements ICommandHandler {

	public static final String CMD_ID = "init";

	@Inject
	DolphinSessionBeans dolphinSessionBeans;

    @Inject
	Appender appender;

	@Override
	public void handleCommand(CommandEvent commandEvent) {

		// Create PM:
		DTO dto = new DTO(new Slot(Constants.MY_ATTRIBUTE, "") );
		dolphinSessionBeans.getServerDolphin().presentationModel(Constants.MY_PM, null, dto);

		// Init PM:
		getServerDolphin().getAt(PM_APP).getAt(ATT_NAME).setValue("Duke");


		// Get a handle to the PM which was initially created by the client (see makeitlonger.jsp):
		ServerModelStore modelStore = dolphinSessionBeans.getModelStore();
		PresentationModel pm = modelStore.findPresentationModelById(Constants.MY_PM);

		// get the PM's attribute 'myAttribute':
		final Attribute at = pm.getAt(Constants.MY_ATTRIBUTE);

		// Change the value of the attribute. change is immediately visible on the client:
		final String oldValue = (String)at.getValue();
		String newValue = appender.makeItLonger(oldValue);
//		at.setValue(newValue);
		ServerDolphin.changeValue(commandEvent.getResponse(), (ServerAttribute) at, newValue);
	}
}
