package org.opendolphin.jee.server;

import org.junit.Before;
import org.junit.Test;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.CreatePresentationModelCommand;

import static org.junit.Assert.assertEquals;

public class CreatePresentationModelCommandHandlerTest {

	private CreatePresentationModelCommandHandler handler;
	private ModelStoreHolder modelStoreHolder;

	@Before
	public void setUp() throws Exception {
		handler = new CreatePresentationModelCommandHandler();
		modelStoreHolder = new ModelStoreHolder();
		handler.modelStoreHolder = modelStoreHolder;
		handler.init();
	}

	@Test
	public void commandId() throws Exception {
		assertEquals(CreatePresentationModelCommandHandler.COMMAND_ID, Command.idFor(CreatePresentationModelCommand.class));
	}

}
