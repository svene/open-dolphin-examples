package org.opendolphin.jee.server;

import org.opendolphin.core.comm.CreatePresentationModelCommand;
import org.opendolphin.core.server.action.CreatePresentationModelAction;
import org.opendolphin.core.server.comm.CommandHandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
//@DolphinCommandHandler(CreatePresentationModelCommandHandler.COMMAND_ID)
@SuppressWarnings("unused")
public class CreatePresentationModelCommandHandler implements ICommandHandler {

	static final String COMMAND_ID = "CreatePresentationModel";

	@Inject
	ModelStoreHolder modelStoreHolder;
	private CommandHandler<CreatePresentationModelCommand> commandHandler;

	@PostConstruct
	public void init() {
		commandHandler = DolphinCommands.commandHandlerFromAction(new CreatePresentationModelAction(), CreatePresentationModelCommand.class, modelStoreHolder.getModelStore());
	}

	@Override
	public void handleCommand(CommandEvent commandEvent) {
		commandHandler.handleCommand((CreatePresentationModelCommand) commandEvent.getCommand(), commandEvent.getResponse());
	}
}
