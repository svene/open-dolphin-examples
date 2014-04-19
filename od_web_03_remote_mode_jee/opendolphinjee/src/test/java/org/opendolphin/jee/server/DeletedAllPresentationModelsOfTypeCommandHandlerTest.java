package org.opendolphin.jee.server;

import org.junit.Test;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.DeletedAllPresentationModelsOfTypeNotification;

import static org.junit.Assert.assertEquals;

public class DeletedAllPresentationModelsOfTypeCommandHandlerTest {

	@Test
	public void commandId() throws Exception {
		assertEquals(DeletedAllPresentationModelsOfTypeCommandHandler.COMMAND_ID, Command.idFor(DeletedAllPresentationModelsOfTypeNotification.class));
	}
}
