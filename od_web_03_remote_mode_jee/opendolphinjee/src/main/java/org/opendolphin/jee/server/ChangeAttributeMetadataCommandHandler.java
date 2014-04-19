package org.opendolphin.jee.server;

import org.opendolphin.core.comm.ChangeAttributeMetadataCommand;
import org.opendolphin.core.server.action.StoreAttributeAction;
import org.opendolphin.core.server.comm.CommandHandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * From OpenDolphin's StoreAttributeAction.groovy
 */
@Singleton
@DolphinCommandHandler(ChangeAttributeMetadataCommandHandler.COMMAND_ID)
@SuppressWarnings("unused")
public class ChangeAttributeMetadataCommandHandler implements ICommandHandler {

	static final String COMMAND_ID = "ChangeAttributeMetadata";

	@Inject
	ModelStoreHolder modelStoreHolder;

	private CommandHandler<ChangeAttributeMetadataCommand> commandHandler;

	@PostConstruct
	public void init() {
		commandHandler = DolphinCommands.commandHandlerFromAction(new StoreAttributeAction(), ChangeAttributeMetadataCommand.class, modelStoreHolder.getModelStore());
	}

	@Override
	public void handleCommand(CommandEvent commandEvent) {
		commandHandler.handleCommand((ChangeAttributeMetadataCommand) commandEvent.getCommand(), commandEvent.getResponse());

	}
}
