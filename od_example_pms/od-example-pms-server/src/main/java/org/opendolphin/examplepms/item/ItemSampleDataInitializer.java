package org.opendolphin.examplepms.item;

import org.opendolphin.core.server.ServerDolphin;

public class ItemSampleDataInitializer {

	public void initialize(ServerDolphin serverDolphin) {

		ItemServerAPI.newPM(serverDolphin, "PM1", "Sven");
		ItemServerAPI.newPM(serverDolphin, "PM2", "Uli");
	}

}
