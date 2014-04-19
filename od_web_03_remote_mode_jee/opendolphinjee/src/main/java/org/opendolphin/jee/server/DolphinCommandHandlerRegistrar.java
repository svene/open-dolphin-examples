package org.opendolphin.jee.server;

import org.opendolphin.core.ModelStore;
import org.opendolphin.core.comm.*;
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

		final CommandHandler commandHandler = DolphinCommands.commandHandlerFromAction(dolphinServerAction, commandClass, modelStore);
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


}
