package org.opendolphin.example.masterdetail;

import org.opendolphin.core.server.ServerDolphin;

public class SampleDataInitializer {

	public void initialize(ServerDolphin serverDolphin) {

		serverDolphin.presentationModel("PM1", ItemApi.ITEM_TYPE, ItemApi.newDTO("Sven", "Hello"));
		serverDolphin.presentationModel("PM2", ItemApi.ITEM_TYPE, ItemApi.newDTO("Uli", "Good Morning"));
	}

}
