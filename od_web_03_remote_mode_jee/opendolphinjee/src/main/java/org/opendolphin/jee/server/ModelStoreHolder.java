package org.opendolphin.jee.server;

import org.opendolphin.core.ModelStore;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;

@SessionScoped
public class ModelStoreHolder implements Serializable {

	ModelStore modelStore = new ModelStore();//6

	private final static Logger LOGGER = Logger.getLogger(ModelStoreHolder.class.getName());

	@PostConstruct
	public void init() {
		LOGGER.info("new ModelStoreHolder created");
	}

	public ModelStore getModelStore() {
		return modelStore;
	}
}
