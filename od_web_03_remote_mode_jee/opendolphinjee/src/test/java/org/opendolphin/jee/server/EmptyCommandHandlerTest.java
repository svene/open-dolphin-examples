package org.opendolphin.jee.server;

import org.junit.Test;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.EmptyNotification;

import static org.junit.Assert.assertEquals;

public class EmptyCommandHandlerTest {

	@Test
	public void commandId() throws Exception {
		assertEquals(EmptyCommandHandler.COMMAND_ID, Command.idFor(EmptyNotification.class));
	}

}
