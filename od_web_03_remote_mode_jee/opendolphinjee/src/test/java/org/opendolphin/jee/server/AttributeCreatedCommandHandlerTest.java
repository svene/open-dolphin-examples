package org.opendolphin.jee.server;

import org.junit.Before;
import org.junit.Test;
import org.opendolphin.core.Attribute;
import org.opendolphin.core.ModelStore;
import org.opendolphin.core.comm.AttributeCreatedNotification;
import org.opendolphin.core.comm.Command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * see OpenDolphin's StoreAttributeActionTests
 */
public class AttributeCreatedCommandHandlerTest {

	private static final String PM_ID = "model";
	private static final String PROPERTY_NAME = "newAttribute";
	private static final String VALUE = "value";
	private AttributeCreatedCommandHandler handler;
	private ModelStoreHolder modelStoreHolder;

	@Before
	public void setUp() throws Exception {
		handler = new AttributeCreatedCommandHandler();
		modelStoreHolder = new ModelStoreHolder();
		handler.modelStoreHolder = modelStoreHolder;
		handler.init();
	}

	@Test
	public void commandId() throws Exception {
		assertEquals(AttributeCreatedCommandHandler.COMMAND_ID, Command.idFor(AttributeCreatedNotification.class));
	}

	@Test
	public void testStoreAttribute() throws Exception {

		// given:
		AttributeCreatedNotification command = new AttributeCreatedNotification();
		command.setPmId(PM_ID);
		command.setPropertyName(PROPERTY_NAME);
		command.setNewValue(VALUE);

		// when:
		handler.handleCommand(new CommandEvent(command));

		// then:
		ModelStore modelStore = modelStoreHolder.getModelStore();
		assertNotNull(modelStore.findPresentationModelById(PM_ID));
		Attribute attribute = modelStore.findPresentationModelById(PM_ID).findAttributeByPropertyName(PROPERTY_NAME);
		assertNotNull(attribute);
		assertEquals(VALUE, attribute.getValue());
	}


}
