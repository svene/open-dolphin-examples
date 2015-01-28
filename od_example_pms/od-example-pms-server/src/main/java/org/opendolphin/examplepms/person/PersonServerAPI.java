package org.opendolphin.examplepms.person;

import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.Slot;

import java.util.function.Supplier;

public class PersonServerAPI {

	public static DTO newDTO(String id, String firstName, String lastName, String birthday) {
		return new DTO(
			newSlot(id, PersonApi.ATT_FIRST_NAME, firstName),
			newSlot(id, PersonApi.ATT_LAST_NAME, lastName),
			newSlot(id, PersonApi.ATT_BIRTHDAY, birthday)
		);
	}

	private static Slot newSlot(String id, String propertyName, String value) {
		return new Slot(propertyName, value, id + "." + propertyName);
	}

	public static Supplier<DTO> newDTOSupplier() {
		return () -> newDTO(null, null, null, null);
	}

}
