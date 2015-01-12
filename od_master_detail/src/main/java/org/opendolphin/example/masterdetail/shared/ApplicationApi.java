package org.opendolphin.example.masterdetail.shared;

public class ApplicationApi {

	public static final String COMMAND_INIT = unique("CMD_INIT");
	public static final String PM_MASTER_DETAIL_ITEM_ID = "MDItem";

	/**
	 * Unify the identifier with the class name prefix.
	 */
	private static String unique(String key) {
		return ApplicationApi.class.getName() + "." + key;
	}

}
