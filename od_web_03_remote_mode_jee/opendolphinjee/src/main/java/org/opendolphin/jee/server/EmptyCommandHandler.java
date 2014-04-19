package org.opendolphin.jee.server;

import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.EmptyNotification;
import org.opendolphin.core.server.action.EmptyAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * From OpenDolphin's StoreAttributeAction.groovy
 */
@Singleton
@DolphinCommandHandler(EmptyCommandHandler.COMMAND_ID)
@SuppressWarnings("unused")
public class EmptyCommandHandler implements ICommandHandler {

	static final String COMMAND_ID = "Empty";

	@Inject
	ModelStoreHolder modelStoreHolder;

	private CommandHandler<EmptyNotification> commandHandler;

	@PostConstruct
	public void init() {
		ActionRegistry registry = new ActionRegistry();
		new EmptyAction().registerIn(registry);
		commandHandler = registry.getAt(Command.idFor(EmptyNotification.class)).get(0);
	}

	@Override
	public void handleCommand(CommandEvent commandEvent) {
		commandHandler.handleCommand((EmptyNotification) commandEvent.getCommand(), commandEvent.getResponse());

	}
}
