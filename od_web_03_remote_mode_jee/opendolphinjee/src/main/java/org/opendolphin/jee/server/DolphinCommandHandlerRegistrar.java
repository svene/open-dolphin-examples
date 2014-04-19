package org.opendolphin.jee.server;

import org.opendolphin.core.ModelStore;
import org.opendolphin.core.comm.*;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.action.*;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;

import java.util.Map;

class DolphinCommandHandlerRegistrar {

	private final Map<String, ICommandHandler> handlerMap;
	private final ModelStore modelStore;


	public DolphinCommandHandlerRegistrar(Map<String, ICommandHandler> handlerMap, ModelStore modelStore) {

		this.handlerMap = handlerMap;
		this.modelStore = modelStore;
	}

	public void registerDolphinCommandHandlers() {

		registerDolphinCommandHandler(new StoreValueChangeAction(), ValueChangedCommand.class);
		registerDolphinCommandHandler(new StoreAttributeAction(), AttributeCreatedNotification.class);
		registerDolphinCommandHandler(new StoreAttributeAction(), ChangeAttributeMetadataCommand.class);
		registerDolphinCommandHandler(new CreatePresentationModelAction(), CreatePresentationModelCommand.class);
		registerDolphinCommandHandler(new BaseValueChangeAction(), BaseValueChangedCommand.class);
		registerDolphinCommandHandler(new DeletePresentationModelAction(), DeletedPresentationModelNotification.class);
		registerDolphinCommandHandler(new DeletedAllPresentationModelsOfTypeAction(), DeletedAllPresentationModelsOfTypeNotification.class);

		registerEmptyCommand();
	}

	private void registerDolphinCommandHandler(DolphinServerAction dolphinServerAction, Class commandClass) {

		final CommandHandler commandHandler = commandHandlerFromAction(dolphinServerAction, commandClass, modelStore);
		handlerMap.put(Command.idFor(commandClass), new ICommandHandler() {
			@Override
			public void handleCommand(CommandEvent commandEvent) {
				commandHandler.handleCommand(commandEvent.getCommand(), commandEvent.getResponse());
			}
		});
	}

	private void registerEmptyCommand() {
		ActionRegistry registry = new ActionRegistry();
		new EmptyAction().registerIn(registry);
		final CommandHandler commandHandler = registry.getAt(Command.idFor(EmptyNotification.class)).get(0);
		handlerMap.put(Command.idFor(EmptyNotification.class), new ICommandHandler() {
			@Override
			public void handleCommand(CommandEvent commandEvent) {
				commandHandler.handleCommand((EmptyNotification) commandEvent.getCommand(), commandEvent.getResponse());
			}
		});
	}

	public static <T extends Command> CommandHandler<T> commandHandlerFromAction(DolphinServerAction dolphinServerAction, Class<T> commandClass, ModelStore modelStore) {

		// TODO: think about changing Dolphin implementation of StoreValueChangeAction to depend only on ModelStore and not on ServerDolphin
		dolphinServerAction.setServerDolphin(new ServerDolphin(modelStore, null));

		// TODO: ActionRegistry is only needed to get access to the CommandHandler, since it is private in CreatePresentationModelAction (CreatePresentationModelAction.createPresentationModel)
		ActionRegistry registry = new ActionRegistry();
		dolphinServerAction.registerIn(registry);
		return registry.getAt(Command.idFor(commandClass)).get(0);
	}


}
