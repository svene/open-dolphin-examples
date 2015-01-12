package org.opendolphin.client.example.masterdetail;

import org.opendolphin.core.Attribute;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.example.masterdetail.shared.ItemApi;

public class Items {

	public static Attribute getNameAttribute(PresentationModel pm) {
		return pm.getAt(ItemApi.ATT_NAME);
	}
	public static String getName(PresentationModel pm) {
		return (String) pm.getAt(ItemApi.ATT_NAME).getValue();
	}
}
