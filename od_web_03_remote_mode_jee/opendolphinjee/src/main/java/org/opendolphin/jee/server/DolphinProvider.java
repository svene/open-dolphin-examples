package org.opendolphin.jee.server;

import org.opendolphin.core.comm.JsonCodec;
import org.opendolphin.core.server.ServerConnector;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.ServerModelStore;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

public class DolphinProvider {

	@Inject
	DolphinSessionBeans dolphinSessionBeans;

	@Produces
	public ServerConnector serverConnector() {
		ServerConnector result = new ServerConnector();
		result.setCodec(new JsonCodec());
		return result;
	}

	@Produces
	public ServerModelStore serverModelStore() {
		return new ServerModelStore();
	}

	@Produces
	public ServerDolphin serverDolphin() {
		return new ServerDolphin(dolphinSessionBeans.getModelStore(), dolphinSessionBeans.getServerConnector());
	}

	@Produces
	@Named("handlerMap")
	public Map<String, ICommandHandler> handlerMap() {
		return new HashMap<>();
	}

}


