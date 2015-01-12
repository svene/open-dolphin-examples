package org.opendolphin.example.masterdetail;

import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.example.masterdetail.shared.ItemApi;

public class SampleDataInitializer {

	public void initialize(ServerDolphin serverDolphin) {

		serverDolphin.presentationModel("PM1", ItemApi.ITEM_TYPE, ItemServerAPI.newDTO("Sven"));
		serverDolphin.presentationModel("PM2", ItemApi.ITEM_TYPE, ItemServerAPI.newDTO("Uli"));
	}

}
