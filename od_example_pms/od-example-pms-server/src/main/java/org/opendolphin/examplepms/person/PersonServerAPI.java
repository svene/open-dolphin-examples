package org.opendolphin.examplepms.person;

import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.Slot;

import java.util.function.Supplier;

public class PersonServerAPI {

	public static DTO newDTO(String firstName, String lastName, String birthday) {
		return new DTO(
			new Slot(PersonApi.ATT_FIRST_NAME, firstName),
			new Slot(PersonApi.ATT_LAST_NAME, lastName),
			new Slot(PersonApi.ATT_BIRTHDAY, birthday)
		);
	}

	public static Supplier<DTO> newDTOSupplier() {
		return () -> newDTO(null, null, null);
	}

}
