package org.opendolphin.jee.server;

import org.opendolphin.core.comm.AttributeCreatedNotification;
import org.opendolphin.core.server.action.StoreAttributeAction;
import org.opendolphin.core.server.comm.CommandHandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * From OpenDolphin's StoreAttributeAction.groovy
 */
@Singleton
@DolphinCommandHandler(AttributeCreatedCommandHandler.COMMAND_ID)
@SuppressWarnings("unused")
public class AttributeCreatedCommandHandler implements ICommandHandler {

	static final String COMMAND_ID = "AttributeCreated";

	@Inject
	ModelStoreHolder modelStoreHolder;

	private CommandHandler<AttributeCreatedNotification> commandHandler;

	@PostConstruct
	public void init() {
		commandHandler = DolphinCommands.commandHandlerFromAction(new StoreAttributeAction(), AttributeCreatedNotification.class, modelStoreHolder.getModelStore());
	}

	@Override
	public void handleCommand(CommandEvent commandEvent) {
		commandHandler.handleCommand((AttributeCreatedNotification) commandEvent.getCommand(), commandEvent.getResponse());
	}
}
