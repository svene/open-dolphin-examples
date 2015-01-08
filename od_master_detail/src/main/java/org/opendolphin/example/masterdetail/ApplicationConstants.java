package org.opendolphin.example.masterdetail;

/**
 * Place for shared information among client and server. Typically identifiers for models, attributes and actions.
 */
public class ApplicationConstants {

	public static final String PM_APP = unique("APP");

	public static final String COMMAND_INIT = unique("CMD_INIT"), COMMAND_GREET = unique("CMD_GREET");
	public static final String TECHNICAL_ID_POSTFIX = "_technical";


	public interface ItemApi {

		String ATT_NAME = ItemApi.class.getName() + "ATT_NAME";
		String ATT_GREETING = ItemApi.class.getName() + "ATT_GREETING";
		String ITEM_TYPE = ItemApi.class.getName() + "ITEM_TYPE";
	}

	public static class ItemPM {
		public final String id, name, greeting;

		public ItemPM(String id, String name, String greeting) {
			this.id = id;
			this.name = name;
			this.greeting = greeting;
		}
	}
	public static final ItemPM P1 = new ItemPM("PM1", "Sven", "Hello");
	public static final ItemPM P2 = new ItemPM("PM2", "Uli", "Good Morning");

	public static class MasterDetailsApi {
		public static final String TYPE = MasterDetailsApi.class.getName() + "_TYPE";
		public static final String ATT_CURRENT_PM_ID = MasterDetailsApi.class.getName() + "_ATT_CURRENT_PM_ID";

		public static String getCurrentPmId(String masterDetailId) {
			return masterDetailId + "_currentPM";
		}
		public static String getMetaPmId(String masterDetailId) {
			return masterDetailId + "_metaPM";
		}
	}

	public static String getTechnicalType(String type) {
		return type + TECHNICAL_ID_POSTFIX;
	}

	public static class MasterDetail {
		public final String id, currentPMId, metaPMId;

		public MasterDetail(String id) {
			this.id = id;
			currentPMId = id + "_currentPM";
			metaPMId = id + "_metaPM";
		}
	}
	public static MasterDetail MASTER_DETAIL_FOR_ITEMS = new MasterDetail("MDItem");

	/**
	 * Unify the identifier with the class name prefix.
	 */
	private static String unique(String key) {
		return ApplicationConstants.class.getName() + "." + key;
	}

}
