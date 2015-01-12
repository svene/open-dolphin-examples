package org.opendolphin.example.masterdetail;

public class GeneralApi {

	private static final String TECHNICAL_ID_POSTFIX = "_technical";

	public static String getTechnicalType(String type) {
		return type + TECHNICAL_ID_POSTFIX;
	}
}
