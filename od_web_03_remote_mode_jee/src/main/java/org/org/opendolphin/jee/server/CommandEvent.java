package org.org.opendolphin.jee.server;

import org.opendolphin.core.comm.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandEvent {
	private List<Command> response = new ArrayList<>();
	private Command command;

	public CommandEvent(Command command) {
		this.command = command;
	}

	public List<Command> getResponse() {
		return response;
	}

	public Command getCommand() {
		return command;
	}
}
