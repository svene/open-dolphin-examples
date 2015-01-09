package org.opendolphin.example.masterdetail;

import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.Slot;

import java.util.function.Supplier;

public interface ItemApi {

	String ATT_NAME = ItemApi.class.getName() + "ATT_NAME";
	String ATT_GREETING = ItemApi.class.getName() + "ATT_GREETING";
	String ITEM_TYPE = ItemApi.class.getName() + "ITEM_TYPE";


	public static DTO newDTO(String name, String greeting) {
		return new DTO(new Slot(ATT_NAME, name), new Slot(ATT_GREETING, greeting));
	}

	public static Supplier<DTO> newDTOSupplier() {
		return () -> newDTO(null, null);
	}
}
