package org.opendolphin.jee.server;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.ValueChangedCommand;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * From OpenDolphin's ValueChangedAction.groovy
 */
@Singleton
@CommandHandler("ValueChanged")
@SuppressWarnings("unused")
public class IValueChangedCommandHandler implements ICommandHandler {

	@Inject
	ModelStoreHolder modelStore;

	@Override
	public void handleCommand(CommandEvent commandEvent) {
		Command cmd = commandEvent.getCommand();
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


	}
}
