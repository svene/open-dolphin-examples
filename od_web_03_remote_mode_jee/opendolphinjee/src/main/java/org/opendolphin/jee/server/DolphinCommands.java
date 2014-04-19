package org.opendolphin.jee.server;

import org.opendolphin.core.ModelStore;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;

public class DolphinCommands {

	public static <T extends Command> CommandHandler<T> commandHandlerFromAction(DolphinServerAction dolphinServerAction, Class<T> commandClass, ModelStore modelStore) {

			// TODO: think about changing Dolphin implementation of StoreValueChangeAction to depend only on ModelStore and not on ServerDolphin
			dolphinServerAction.setServerDolphin(new ServerDolphin(modelStore, null));

			// TODO: ActionRegistry is only needed to get access to the CommandHandler, since it is private in CreatePresentationModelAction (CreatePresentationModelAction.createPresentationModel)
			ActionRegistry registry = new ActionRegistry();
			dolphinServerAction.registerIn(registry);
		return registry.getAt(Command.idFor(commandClass)).get(0);
	}
}
