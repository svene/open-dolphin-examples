package org.opendolphin.examplepms.item;

import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.Slot;

import java.util.function.Supplier;

public class ItemServerAPI {

	public static DTO newDTO(String name) {
		return new DTO(new Slot(ItemApi.ATT_NAME, name) );
	}

	public static Supplier<DTO> newDTOSupplier() {
		return () -> newDTO(null);
	}

}
