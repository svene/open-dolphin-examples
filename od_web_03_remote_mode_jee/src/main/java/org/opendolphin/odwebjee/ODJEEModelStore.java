package org.opendolphin.odwebjee;

import org.opendolphin.core.ModelStore;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class ODJEEModelStore implements Serializable {

	ModelStore modelStore = new ModelStore();

	public ModelStore getModelStore() {
		return modelStore;
	}
}
