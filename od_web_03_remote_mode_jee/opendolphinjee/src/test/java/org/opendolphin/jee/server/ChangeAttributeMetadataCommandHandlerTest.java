package org.opendolphin.jee.server;

import org.junit.Before;
import org.junit.Test;
import org.opendolphin.core.comm.ChangeAttributeMetadataCommand;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.server.ServerAttribute;
import org.opendolphin.core.server.ServerPresentationModel;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * see OpenDolphin's StoreAttributeActionTests
 */
public class ChangeAttributeMetadataCommandHandlerTest {

	private static final String PM_ID = "model";
	private static final String PROPERTY_NAME = "newAttribute";
	private static final String VALUE = "newValue";
	private ChangeAttributeMetadataCommandHandler handler;
	private ModelStoreHolder modelStoreHolder;

	@Before
	public void setUp() throws Exception {
		handler = new ChangeAttributeMetadataCommandHandler();
		modelStoreHolder = new ModelStoreHolder();
		handler.modelStoreHolder = modelStoreHolder;
		handler.init();
	}

	@Test
	public void commandId() throws Exception {
		assertEquals(ChangeAttributeMetadataCommandHandler.COMMAND_ID, Command.idFor(ChangeAttributeMetadataCommand.class));
	}

	@Test
	public void testChangeAttributeMetadata_AttributeNotFound() throws Exception {

		// given:
		ServerAttribute attribute = new ServerAttribute(PROPERTY_NAME, "");
		ChangeAttributeMetadataCommand command = new ChangeAttributeMetadataCommand();
		command.setAttributeId(attribute.getId());
		command.setMetadataName("dirty");
		command.setValue(true);

		// when:
		handler.handleCommand(new CommandEvent(command));

		// then:
		assertFalse(attribute.isDirty());
	}

	@Test
	public void testChangeAttributeMetadata() throws Exception {

		// given:
		ServerAttribute attribute = new ServerAttribute(PROPERTY_NAME, "");
		ServerPresentationModel pm = new ServerPresentationModel(PM_ID, Arrays.asList(attribute));
		modelStoreHolder.getModelStore().add(pm);

		ChangeAttributeMetadataCommand command = new ChangeAttributeMetadataCommand();
		command.setAttributeId(attribute.getId());
		command.setMetadataName("value");
		command.setValue(VALUE);

		// when:
		handler.handleCommand(new CommandEvent(command));

		// then:
		assertEquals(VALUE, attribute.getValue());
	}
}
