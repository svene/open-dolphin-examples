package org.opendolphin.examplepms.item;

import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.Slot;
import org.opendolphin.examplepms.person.PersonApi;

import java.util.function.Supplier;

public class ItemServerAPI {

	public static ServerPresentationModel newPM(ServerDolphin serverDolphin, String id, String name) {
		return serverDolphin.presentationModel(id, ItemApi.ITEM_TYPE, newDTO(id, name));
	}

	public static DTO newDTO(String id, String name) {
		return new DTO(newSlot(id, ItemApi.ATT_NAME, name) );
	}

	public static Supplier<DTO> newDTOSupplier() {
		return () -> newDTO(null, null);
	}

	private static Slot newSlot(String id, String propertyName, String value) {
		return new Slot(propertyName, value, id + "." + propertyName);
	}


}
