package org.opendolphin.odwebjee;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.ValueChangedCommand;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * From OpenDolphin's ValueChangedAction.groovy
 */@Singleton
@SuppressWarnings("unused")
public class ODJEEValueChangedCommandHandler implements ODJEECommandHandler {

	@Inject
	ODJEEModelStoreHolder modelStore;

	public void consumeCommand(@Observes ODJEECommandEvent event) {
		Command cmd = event.getCommand();
		String id = cmd.getId();
		if (!"ValueChanged".equals(id)) return;
		ValueChangedCommand command = (ValueChangedCommand) cmd;

		Attribute attribute = modelStore.getModelStore().findAttributeById(command.getAttributeId());
		if (attribute != null) {
			attribute.setValue(command.getNewValue());
		} else {
//			log.severe("cannot find attribute with id $command.attributeId to change value from '$command.oldValue' to '$command.newValue'. " +
//				"Known attribute ids are: "+ serverDolphin.serverModelStore.listPresentationModels()*.attributes*.id )
			System.out.println("cannot find attribute with id $command.attributeId to change value from '" +
				command.getOldValue() + "' to '" + command.getNewValue() + "'. "
//				+ "Known attribute ids are: "+ modelStoreHolder.getModelStore().listPresentationModels()*.attributes*.id
			);
		}


	}}
