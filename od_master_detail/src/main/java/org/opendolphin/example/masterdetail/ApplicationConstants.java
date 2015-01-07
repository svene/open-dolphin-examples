package org.opendolphin.example.masterdetail;

/**
 * Place for shared information among client and server. Typically identifiers for models, attributes and actions.
 */
public class ApplicationConstants {

	public static final String PM_APP = unique("APP");
	public static final String ATT_NAME = "ATT_NAME";
	public static final String ATT_GREETING = "ATT_GREETING";

	public static final String COMMAND_INIT = unique("CMD_INIT");
	public static final String COMMAND_GREET = unique("CMD_GREET");
	public static final String ITEM_TYPE = "ITEM_TYPE";
	public static final String ITEM_MASTER_DETAIL_ID = "ITEM_MASTER_DETAIL_ID";
	public static final String CURRENT_ITEM_ID_POSTFIX = "_currentItem";
	public static final String TECHNICAL_ID_POSTFIX = "_technical";

	public static final String META_PM_ID_POSTFIX = "_meta";
	public static final String TYPE_MASTER_DETAIL_META = "TYPE_MASTER_DETAIL_META";
	public static final String ATT_META_CURRENT_PM_ID = "ATT_META_CURRENT_PM_ID";

	public static final String PM1_NAME = "Sven";
	public static final String PM2_NAME = "Uli";
	public static final String PM1_GREETING = "Hello";
	public static final String PM2_GREETING = "Good Morning";


	/**
	 * Unify the identifier with the class name prefix.
	 */
	private static String unique(String key) {
		return ApplicationConstants.class.getName() + "." + key;
	}

}
