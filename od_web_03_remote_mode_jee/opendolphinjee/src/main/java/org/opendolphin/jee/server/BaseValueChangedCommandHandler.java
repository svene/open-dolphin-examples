package org.opendolphin.jee.server;

import org.opendolphin.core.comm.BaseValueChangedCommand;
import org.opendolphin.core.server.action.BaseValueChangeAction;
import org.opendolphin.core.server.comm.CommandHandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
//@DolphinCommandHandler(BaseValueChangedCommandHandler.COMMAND_ID)
@SuppressWarnings("unused")
public class BaseValueChangedCommandHandler implements ICommandHandler {

	static final String COMMAND_ID = "BaseValueChanged";

	@Inject
	ModelStoreHolder modelStoreHolder;

	private CommandHandler<BaseValueChangedCommand> commandHandler;

	@PostConstruct
	public void init() {
		commandHandler = DolphinCommands.commandHandlerFromAction(new BaseValueChangeAction(), BaseValueChangedCommand.class, modelStoreHolder.getModelStore());
	}

	@Override
	public void handleCommand(CommandEvent commandEvent) {
		commandHandler.handleCommand((BaseValueChangedCommand) commandEvent.getCommand(), commandEvent.getResponse());
	}

}
