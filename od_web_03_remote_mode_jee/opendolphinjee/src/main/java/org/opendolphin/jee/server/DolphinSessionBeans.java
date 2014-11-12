package org.opendolphin.jee.server;

import org.opendolphin.core.server.ServerConnector;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.ServerModelStore;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
public class DolphinSessionBeans implements Serializable {

	@Inject
	transient ServerModelStore modelStore;

	@Inject
	transient ServerConnector serverConnector;

	@Inject
	transient ServerDolphin serverDolphin;

	public ServerModelStore getModelStore() {
		return modelStore;
	}

	public ServerConnector getServerConnector() {
		return serverConnector;
	}

	public ServerDolphin getServerDolphin() {
		return serverDolphin;
	}

}
