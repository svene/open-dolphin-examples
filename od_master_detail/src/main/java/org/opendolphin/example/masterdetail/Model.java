package org.opendolphin.example.masterdetail;

import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerDolphin;

import java.util.function.Supplier;

public class Model {

	public final PresentationModel currentItem;
	private final String masterDetailId;

	public Model(ServerDolphin dolphin, String masterDetailId, String type, Supplier<DTO> dtoSupplier) {
		this.masterDetailId = masterDetailId;
		currentItem = dolphin.presentationModel(masterDetailId + "_currentItem", type + "_technical", dtoSupplier.get() );
	}
}
