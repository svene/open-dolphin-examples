package org.opendolphin.examplepms.person;

import org.opendolphin.core.server.ServerDolphin;

public class PersonSampleDataInitializer {

	public void initialize(ServerDolphin serverDolphin) {

		PersonServerAPI.newPM(serverDolphin, "PM1", "Sven", "Ehrke", "31.10.1967");
		PersonServerAPI.newPM(serverDolphin, "PM2", "Uli", "Tretter", "7.7.1969");
	}


}
