package org.opendolphin.jee.server;

import org.opendolphin.core.comm.Command;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

class CommandDispatcher {

	@Inject
	CommandHandlerRegistry commandHandlerRegistry;

	List<Command> dispatchCommands(List<Command> commands) {
		List<Command> results = new ArrayList<>();
		for (Command command : commands) {
			ICommandHandler commandHandler = commandHandlerRegistry.commandHandlerByKey(command.getId());
			if (commandHandler != null) {
				CommandEvent ce = new CommandEvent(command);
				commandHandler.handleCommand(ce);
				results.addAll(ce.getResponse());
			}
		}
		return results;
	}
}
