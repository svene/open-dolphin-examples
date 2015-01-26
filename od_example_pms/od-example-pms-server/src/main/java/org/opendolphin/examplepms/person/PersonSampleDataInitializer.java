package org.opendolphin.examplepms.person;

import org.opendolphin.core.server.ServerDolphin;

public class PersonSampleDataInitializer {

	public void initialize(ServerDolphin serverDolphin) {

		serverDolphin.presentationModel("PM1", PersonApi.PERSON_TYPE, PersonServerAPI.newDTO("Sven", "Ehrke", "31.10.1967"));
		serverDolphin.presentationModel("PM2", PersonApi.PERSON_TYPE, PersonServerAPI.newDTO("Uli", "Ehrke", "7.7.1969"));
	}

}
