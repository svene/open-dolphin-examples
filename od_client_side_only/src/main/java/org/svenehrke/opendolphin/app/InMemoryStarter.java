package org.svenehrke.opendolphin.app;

import javafx.application.Application;
import org.opendolphin.extension.InMemoryJavaFXDolphinStarter;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStarter {

	public static void main(String[] args) {

		Map<Integer, Class<? extends Application>> apps = new HashMap<>();

		apps.put(1, App01.class);

		int appId = 1;

		new InMemoryJavaFXDolphinStarter().start(apps.get(appId));

	}
}
