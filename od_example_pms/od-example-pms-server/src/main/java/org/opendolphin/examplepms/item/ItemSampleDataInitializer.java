package org.opendolphin.examplepms.item;

import org.opendolphin.core.server.ServerDolphin;

public class ItemSampleDataInitializer {

	public void initialize(ServerDolphin serverDolphin) {

		serverDolphin.presentationModel("PM1", ItemApi.ITEM_TYPE, ItemServerAPI.newDTO("Sven"));
		serverDolphin.presentationModel("PM2", ItemApi.ITEM_TYPE, ItemServerAPI.newDTO("Uli"));
	}

}
