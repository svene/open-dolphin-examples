package org.opendolphin.examplepms.person;

import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.ServerPresentationModel;

public class PersonSampleDataInitializer {

	public void initialize(ServerDolphin serverDolphin) {

		newPM(serverDolphin, "PM1", "Sven", "Ehrke", "31.10.1967");
		newPM(serverDolphin, "PM2", "Uli", "Ehrke", "7.7.1969");
	}

	private ServerPresentationModel newPM(ServerDolphin serverDolphin, String id, String firstName, String lastName, String birthday) {
		return serverDolphin.presentationModel(id, PersonApi.PERSON_TYPE, PersonServerAPI.newDTO(id, firstName, lastName, birthday));
	}

}
