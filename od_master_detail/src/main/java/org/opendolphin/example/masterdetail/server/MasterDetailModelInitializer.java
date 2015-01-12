package org.opendolphin.example.masterdetail.server;

import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.Slot;

import java.util.function.Supplier;

import static org.opendolphin.example.masterdetail.shared.MasterDetailApi.*;

public final class MasterDetailModelInitializer {

	private MasterDetailModelInitializer() {
	}

	public static void initialize(ServerDolphin dolphin, String id, String type, Supplier<DTO> dtoSupplier) {

		dolphin.presentationModel(getCurrentPmId(id), GeneralApi.getTechnicalType(type), dtoSupplier.get());
		createMetaPM(dolphin, id);
		addCurrentPMIdHandler(dolphin, id);
	}

	private static PresentationModel createMetaPM(ServerDolphin dolphin, String id) {
		return dolphin.presentationModel(getMetaPmId(id), TYPE_MASTER_DETAIL_META,
			new DTO(new Slot(ATT_CURRENT_PM_ID, null))
		);
	}

	private static void addCurrentPMIdHandler(ServerDolphin dolphin, String id) {
		PresentationModel currentItem = dolphin.getAt(getCurrentPmId(id));
		PresentationModel metaPM = dolphin.getAt(getMetaPmId(id));
		metaPM.getAt(ATT_CURRENT_PM_ID).addPropertyChangeListener(evt -> {
			if (!(evt.getNewValue() instanceof String)) {
				return;
			}
			String pmId = (String) evt.getNewValue();
			ServerPresentationModel pm = dolphin.findPresentationModelById(pmId);
			if (pm != null) {
				currentItem.syncWith(pm);
			}
		});
	}

}
