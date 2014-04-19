package org.opendolphin.jee.server;

import org.junit.Test;
import org.opendolphin.core.comm.BaseValueChangedCommand;
import org.opendolphin.core.comm.Command;

import static org.junit.Assert.assertEquals;

public class BaseValueChangedCommandHandlerTest {

	@Test
	public void commandId() throws Exception {
		assertEquals(BaseValueChangedCommandHandler.COMMAND_ID, Command.idFor(BaseValueChangedCommand.class));
	}

}
