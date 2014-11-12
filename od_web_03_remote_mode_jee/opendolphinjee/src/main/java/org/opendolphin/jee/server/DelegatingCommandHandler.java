package org.opendolphin.jee.server;

import org.opendolphin.core.server.comm.CommandHandler;

public class DelegatingCommandHandler implements ICommandHandler {
	private final CommandHandler odCommandHandler;

	public DelegatingCommandHandler(CommandHandler odCommandHandler) {
		this.odCommandHandler = odCommandHandler;
	}

	@Override
	public void handleCommand(CommandEvent commandEvent) {
		odCommandHandler.handleCommand(commandEvent.getCommand(), commandEvent.getResponse());
	}
}
