package org.svenehrke.opendolphin.app;

import javafx.application.Application;
import org.opendolphin.extension.InMemoryJavaFXDolphinStarter;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStarter {

	public static void main(String[] args) {

		Map<Integer, Class<? extends Application>> apps = new HashMap<>();

		apps.put(1, App01.class);
		apps.put(2, App02.class);

		int appId = 2;

		new InMemoryJavaFXDolphinStarter().start(apps.get(appId));

	}
}
