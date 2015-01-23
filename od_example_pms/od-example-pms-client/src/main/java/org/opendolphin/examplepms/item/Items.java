package org.opendolphin.examplepms.item;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;

public class Items {

	public static Attribute getNameAttribute(PresentationModel pm) {
		return pm.getAt(ItemApi.ATT_NAME);
	}
	public static String getName(PresentationModel pm) {
		return (String) pm.getAt(ItemApi.ATT_NAME).getValue();
	}
}
