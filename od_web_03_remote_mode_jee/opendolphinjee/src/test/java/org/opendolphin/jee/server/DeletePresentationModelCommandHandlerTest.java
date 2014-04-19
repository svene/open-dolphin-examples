package org.opendolphin.jee.server;

import org.junit.Test;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.DeletedPresentationModelNotification;

import static org.junit.Assert.assertEquals;

public class DeletePresentationModelCommandHandlerTest {

	@Test
	public void commandId() throws Exception {
		assertEquals(DeletePresentationModelCommandHandler.COMMAND_ID, Command.idFor(DeletedPresentationModelNotification.class));
	}

}
