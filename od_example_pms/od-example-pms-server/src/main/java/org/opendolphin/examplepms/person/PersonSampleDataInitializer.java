package org.opendolphin.examplepms.person;

import org.opendolphin.core.server.ServerDolphin;

public class PersonSampleDataInitializer {

	public static final String PM_1 = "PM1";
	public static final String PM_2 = "PM2";

	public void initialize(ServerDolphin serverDolphin) {

		PersonServerAPI.newPM(serverDolphin, PM_1, "Sven", "Ehrke", "31.10.1967");
		PersonServerAPI.newPM(serverDolphin, PM_2, "Uli", "Tretter", "7.7.1969");
	}


}
