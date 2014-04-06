package org.opendolphin.odwebjee;

import org.opendolphin.core.comm.Command;

import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class ODJEEServerConnector {


	/** doesn't fail on missing commands **/
	List<Command> receive(Command command) {
		List<Command> response = new LinkedList(); // collecting parameter pattern
		return response;
	}

}
