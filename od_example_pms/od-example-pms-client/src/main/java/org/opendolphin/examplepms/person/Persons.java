package org.opendolphin.examplepms.person;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.examplepms.item.ItemApi;

public class Persons {

	public static Attribute getFirstNameAttribute(PresentationModel pm) {
		return pm.getAt(PersonApi.ATT_FIRST_NAME);
	}
	public static String getName(PresentationModel pm) {
		return (String) getFirstNameAttribute(pm).getValue();
	}

	public static Attribute getLastNameAttribute(PresentationModel pm) {
		return pm.getAt(PersonApi.ATT_LAST_NAME);
	}
	public static String getLastName(PresentationModel pm) {
		return (String) getLastNameAttribute(pm).getValue();
	}

	public static Attribute getBirthdayAttribute(PresentationModel pm) {
		return pm.getAt(PersonApi.ATT_BIRTHDAY);
	}
	public static String getBirthday(PresentationModel pm) {
		return (String) getBirthdayAttribute(pm).getValue();
	}

}
