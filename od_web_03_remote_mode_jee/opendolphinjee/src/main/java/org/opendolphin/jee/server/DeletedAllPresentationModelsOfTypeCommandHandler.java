package org.opendolphin.jee.server;

import org.opendolphin.core.comm.DeletedAllPresentationModelsOfTypeNotification;
import org.opendolphin.core.server.action.DeletedAllPresentationModelsOfTypeAction;
import org.opendolphin.core.server.comm.CommandHandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
//@DolphinCommandHandler(DeletedAllPresentationModelsOfTypeCommandHandler.COMMAND_ID)
@SuppressWarnings("unused")
public class DeletedAllPresentationModelsOfTypeCommandHandler implements ICommandHandler {

	static final String COMMAND_ID = "DeletedAllPresentationModelsOfType";

	@Inject
	ModelStoreHolder modelStoreHolder;

	private CommandHandler<DeletedAllPresentationModelsOfTypeNotification> commandHandler;

	@PostConstruct
	public void init() {
		commandHandler = DolphinCommands.commandHandlerFromAction(new DeletedAllPresentationModelsOfTypeAction(), DeletedAllPresentationModelsOfTypeNotification.class, modelStoreHolder.getModelStore());
	}

	@Override
	public void handleCommand(CommandEvent commandEvent) {
		commandHandler.handleCommand((DeletedAllPresentationModelsOfTypeNotification) commandEvent.getCommand(), commandEvent.getResponse());
	}

}
