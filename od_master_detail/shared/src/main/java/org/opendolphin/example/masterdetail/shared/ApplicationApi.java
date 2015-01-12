package org.opendolphin.example.masterdetail.shared;

public class ApplicationApi {

	public static final String COMMAND_INIT = unique("CMD_INIT");

	/**
	 * Unify the identifier with the class name prefix.
	 */
	private static String unique(String key) {
		return ApplicationApi.class.getName() + "." + key;
	}

}
