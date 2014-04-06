package org.opendolphin.odwebjee;

import org.opendolphin.core.comm.Command;

import java.util.ArrayList;
import java.util.List;

public class ODJEECommandEvent {
	List<Command> response = new ArrayList<>();
	Command command;

	public ODJEECommandEvent(Command command) {
		this.command = command;
	}

	public List<Command> getResponse() {
		return response;
	}

	public Command getCommand() {
		return command;
	}
}
