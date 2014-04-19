package org.opendolphin.jee.server;

import org.opendolphin.core.comm.DeletedPresentationModelNotification;
import org.opendolphin.core.server.action.DeletePresentationModelAction;
import org.opendolphin.core.server.comm.CommandHandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
@DolphinCommandHandler(DeletePresentationModelCommandHandler.COMMAND_ID)
@SuppressWarnings("unused")
public class DeletePresentationModelCommandHandler implements ICommandHandler {

	static final String COMMAND_ID = "DeletedPresentationModel";

	@Inject
	ModelStoreHolder modelStoreHolder;

	private CommandHandler<DeletedPresentationModelNotification> commandHandler;

	@PostConstruct
	public void init() {
		commandHandler = DolphinCommands.commandHandlerFromAction(new DeletePresentationModelAction(), DeletedPresentationModelNotification.class, modelStoreHolder.getModelStore());
	}

	@Override
	public void handleCommand(CommandEvent commandEvent) {
		commandHandler.handleCommand((DeletedPresentationModelNotification) commandEvent.getCommand(), commandEvent.getResponse());
	}

}
