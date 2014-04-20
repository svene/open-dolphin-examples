package org.opendolphin.jee.server;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SessionScoped
public class CommandHandlerRegistry implements Serializable {

	@Inject
	Instance<ICommandHandler> commandHandlers;

	@Inject
	ModelStoreHolder modelStoreHolder;

	private Map<String, ICommandHandler> handlerMap = new HashMap<>();

	@PostConstruct
	void initialize() {
		new DolphinCommandHandlerRegistrar(handlerMap, modelStoreHolder.getModelStore()).registerDolphinCommandHandlers();
		registerApplicationCommandHandlers();
	}

	ICommandHandler commandHandlerByKey(String key) {
		return handlerMap.get(key);
	}

	private void registerApplicationCommandHandlers() {
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
			handlerMap.put(name, commandHandler);
		}
	}

	private String readKeyFromClass(Class<? extends ICommandHandler> clazz) {
		String sn = clazz.getSimpleName();
		String key = String.valueOf(sn.charAt(0)).toLowerCase();
		key += sn.substring(1).replace("Handler", "");
		System.out.println("key =  " + key);
		return key;
	}


}