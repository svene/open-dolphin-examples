package org.opendolphin.jee.server;

import org.opendolphin.core.comm.ValueChangedCommand;
import org.opendolphin.core.server.action.StoreValueChangeAction;
import org.opendolphin.core.server.comm.CommandHandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
//@DolphinCommandHandler(ValueChangedCommandHandler.COMMAND_ID)
@SuppressWarnings("unused")
public class ValueChangedCommandHandler implements ICommandHandler {

	static final String COMMAND_ID = "ValueChanged";

	@Inject
	ModelStoreHolder modelStoreHolder;

	private CommandHandler<ValueChangedCommand> commandHandler;

	@PostConstruct
	public void init() {
		commandHandler = DolphinCommands.commandHandlerFromAction(new StoreValueChangeAction(), ValueChangedCommand.class, modelStoreHolder.getModelStore());
	}

	@Override
	public void handleCommand(CommandEvent commandEvent) {
		commandHandler.handleCommand((ValueChangedCommand) commandEvent.getCommand(), commandEvent.getResponse());
	}

}
