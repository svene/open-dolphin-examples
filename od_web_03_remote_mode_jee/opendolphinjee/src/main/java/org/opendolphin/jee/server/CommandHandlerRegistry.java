package org.opendolphin.jee.server;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class CommandHandlerRegistry {

	@Inject
	Instance<ICommandHandler> commandHandlers;

	private Map<String, ICommandHandler> handlerMap = new HashMap<>();

	@PostConstruct
	void initialize() {
		for (ICommandHandler commandHandler : commandHandlers) {
			Class<? extends ICommandHandler> clazz = commandHandler.getClass();
			String name;
			if (clazz.isAnnotationPresent(CommandHandler.class)) {
				CommandHandler annotation = clazz.getAnnotation(CommandHandler.class);
				name = annotation.value();
			}
			else {
				String key = readKeyFromClass(clazz);
				name = key;
			}
			handlerMap.put(name, commandHandler);
		}
	}

	ICommandHandler actionByKey(String key) {
		return handlerMap.get(key);
	}

	private String readKeyFromClass(Class<? extends ICommandHandler> clazz) {
		String sn = clazz.getSimpleName();
		String key = String.valueOf(sn.charAt(0)).toLowerCase();
		key += sn.substring(1).replace("Handler", "");
		System.out.println("key =  " + key);
		return key;
	}


}
