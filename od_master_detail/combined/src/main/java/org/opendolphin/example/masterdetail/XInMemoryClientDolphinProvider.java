package org.opendolphin.example.masterdetail;

import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.comm.BlindCommandBatcher;
import org.opendolphin.core.client.comm.InMemoryClientConnector;
import org.opendolphin.core.client.comm.UiThreadHandler;
import org.opendolphin.core.comm.DefaultInMemoryConfig;
import org.opendolphin.core.server.ServerDolphin;

import java.util.HashMap;
import java.util.Map;

public class XInMemoryClientDolphinProvider {

	public static final String KEY_CLIENT_DOLPHIN = "client_dolphin";
	public static final String KEY_SERVER_DOLPHIN = "server_dolphin";

	Map<String, Object> cfg;

	public XInMemoryClientDolphinProvider(UiThreadHandler uiThreadHandler) {
		cfg = newInMemoryConfig(uiThreadHandler);
	}

	private static Map newInMemoryConfig(UiThreadHandler uiThreadHandler) {
		Map result = new HashMap<>();
		DefaultInMemoryConfig config = new DefaultInMemoryConfig();
		BlindCommandBatcher batcher = new BlindCommandBatcher();
		batcher.setDeferMillis(400);
		batcher.setMergeValueChanges(true);
		config.getClientDolphin().setClientConnector(new InMemoryClientConnector(config.getClientDolphin(), batcher));

		//config.clientDolphin.clientConnector.sleepMillis = 100
		InMemoryClientConnector cc = (InMemoryClientConnector) config.getClientDolphin().getClientConnector();
		cc.setServerConnector(config.getServerDolphin().getServerConnector());

		config.getServerDolphin().registerDefaultActions();

		config.getClientDolphin().getClientConnector().setUiThreadHandler(uiThreadHandler);

		result.put(KEY_CLIENT_DOLPHIN, config.getClientDolphin());
		result.put(KEY_SERVER_DOLPHIN, config.getServerDolphin());

		return result;
	}


	public ClientDolphin getClientDolphin() {
		return (ClientDolphin) cfg.get(KEY_CLIENT_DOLPHIN);
	}
	public ServerDolphin getServerDolphin() {
		return (ServerDolphin) cfg.get(KEY_SERVER_DOLPHIN);
	}
}
