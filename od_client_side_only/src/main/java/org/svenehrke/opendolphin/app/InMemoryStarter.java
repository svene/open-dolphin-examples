package org.svenehrke.opendolphin.app;

import javafx.application.Application;
import org.opendolphin.core.client.comm.JavaFXUiThreadHandler;
import org.opendolphinx.extension.InMemoryClientDolphinProvider;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStarter {

	public static void main(String[] args) {

		initializeJavaFXParameters();
		Map<Integer, Class<? extends Application>> apps = readAppMap();
		Application.launch(apps.get(3));
	}

	private static void initializeJavaFXParameters() {

		OdCsoJavaFXApplicationParameters.clientDolphin = new InMemoryClientDolphinProvider(new JavaFXUiThreadHandler()).getClientDolphin();

	}

	private static Map<Integer, Class<? extends Application>> readAppMap() {
		Map<Integer, Class<? extends Application>> apps = new HashMap<>();
		apps.put(1, App01.class);
		apps.put(2, App02.class);
		apps.put(3, App03.class);
		return apps;
	}
}
