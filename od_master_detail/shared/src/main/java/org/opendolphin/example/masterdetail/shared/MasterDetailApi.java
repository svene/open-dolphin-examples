package org.opendolphin.example.masterdetail.shared;

public class MasterDetailApi {

	public static final String TYPE_MASTER_DETAIL_META = MasterDetailApi.class.getName() + "_TYPE";
	public static final String ATT_CURRENT_PM_ID = MasterDetailApi.class.getName() + "_ATT_CURRENT_PM_ID";

	public static String getCurrentPmId(String masterDetailId) {
		return masterDetailId + "_currentPM";
	}

	public static String getMetaPmId(String masterDetailId) {
		return masterDetailId + "_metaPM";
	}

}
