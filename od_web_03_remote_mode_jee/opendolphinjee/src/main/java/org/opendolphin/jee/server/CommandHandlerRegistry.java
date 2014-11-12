package org.opendolphin.jee.server;

import org.opendolphin.core.comm.*;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.action.*;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandHandlerRegistry {

	private final static Logger LOGGER = Logger.getLogger(CommandHandlerRegistry.class.getName());


	@Inject
	Instance<ICommandHandler> commandHandlers;

	@Inject
	DolphinSessionBeans dolphinSessionBeans;

	Map<String, ICommandHandler> handlerMap = new HashMap<>();

	@PostConstruct
	void initialize() {
		registerDolphinCommandHandlers(dolphinSessionBeans.getServerDolphin());
		registerApplicationCommandHandlers();
	}

	ICommandHandler commandHandlerByKey(String key) {
		return handlerMap.get(key);
	}

	private void registerApplicationCommandHandlers() {
		try {
			for (ICommandHandler commandHandler : commandHandlers) {
				Class<? extends ICommandHandler> clazz = commandHandler.getClass();
				String name;
				if (clazz.isAnnotationPresent(DolphinCommandHandler.class)) {
					DolphinCommandHandler annotation = clazz.getAnnotation(DolphinCommandHandler.class);
					name = annotation.value();
				}
				else {
					String key = readKeyFromClass(clazz);
					name = key;
				}
				System.out.println("CommandHandlerRegistry.registerApplicationCommandHandlers: name=" + name);
				handlerMap.put(name, commandHandler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("CommandHandlerRegistry.registerApplicationCommandHandlers");
	}

	public void registerDolphinCommandHandlers(ServerDolphin serverDolphin) {

		registerDolphinCommandHandler(new StoreValueChangeAction(), ValueChangedCommand.class, serverDolphin);
		registerDolphinCommandHandler(new StoreAttributeAction(), AttributeCreatedNotification.class, serverDolphin);
		registerDolphinCommandHandler(new StoreAttributeAction(), ChangeAttributeMetadataCommand.class, serverDolphin);
		registerDolphinCommandHandler(new CreatePresentationModelAction(), CreatePresentationModelCommand.class, serverDolphin);
		registerDolphinCommandHandler(new BaseValueChangeAction(), BaseValueChangedCommand.class, serverDolphin);
		registerDolphinCommandHandler(new DeletePresentationModelAction(), DeletedPresentationModelNotification.class, serverDolphin);
		registerDolphinCommandHandler(new DeletedAllPresentationModelsOfTypeAction(), DeletedAllPresentationModelsOfTypeNotification.class, serverDolphin);

		registerEmptyCommand();
	}

	private void registerDolphinCommandHandler(DolphinServerAction dolphinServerAction, Class commandClass, ServerDolphin serverDolphin) {

		final CommandHandler commandHandler = commandHandlerFromAction(dolphinServerAction, commandClass, serverDolphin);
		handlerMap.put(Command.idFor(commandClass), new DelegatingCommandHandler(commandHandler));
	}
	private static <T extends Command> CommandHandler<T> commandHandlerFromAction(DolphinServerAction dolphinServerAction, Class<T> commandClass, ServerDolphin serverDolphin) {

		// TODO: think about changing Dolphin implementation of StoreValueChangeAction to depend only on ModelStore and not on ServerDolphin
		dolphinServerAction.setServerDolphin(serverDolphin);

		// TODO: ActionRegistry is only needed to get access to the CommandHandler, since it is private in CreatePresentationModelAction (CreatePresentationModelAction.createPresentationModel)
		ActionRegistry registry = new ActionRegistry();
		dolphinServerAction.registerIn(registry);
		return registry.getAt(Command.idFor(commandClass)).get(0);
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

	public void registerCommandHandler(String name, ICommandHandler commandHandler) {
		handlerMap.put(name, commandHandler);
	}

	private String readKeyFromClass(Class<? extends ICommandHandler> clazz) {
		String sn = clazz.getSimpleName();
		String key = String.valueOf(sn.charAt(0)).toLowerCase();
		key += sn.substring(1).replace("Handler", "");
		LOGGER.log(Level.INFO, "key: {0}", new Object[] {""});

		System.out.println("key =  " + key);
		return key;
	}


}
