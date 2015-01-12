package org.opendolphin.example.masterdetail;

import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.Slot;

import java.util.function.Supplier;

import static org.opendolphin.example.masterdetail.shared.ItemApi.*;

public class ItemServerAPI {

	public static DTO newDTO(String name) {
		return new DTO(new Slot(ATT_NAME, name) );
	}

	public static Supplier<DTO> newDTOSupplier() {
		return () -> newDTO(null);
	}

}
