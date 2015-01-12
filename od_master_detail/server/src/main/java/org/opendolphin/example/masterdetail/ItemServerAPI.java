package org.opendolphin.example.masterdetail;

import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.Slot;

import java.util.function.Supplier;

import static org.opendolphin.example.masterdetail.shared.ItemApi.*;

public class ItemServerAPI {

	public static DTO newDTO(String name, String greeting) {
		return new DTO(new Slot(ATT_NAME, name), new Slot(ATT_GREETING, greeting));
	}

	public static Supplier<DTO> newDTOSupplier() {
		return () -> newDTO(null, null);
	}

}
