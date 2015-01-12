package org.opendolphin.server.pattern.masterdetail;

import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.Slot;
import org.opendolphin.example.masterdetail.GeneralApi;

import java.util.function.Supplier;

import static org.opendolphin.shared.pattern.masterdetail.MasterDetailApi.*;

public final class MasterDetailServerApi {

	private MasterDetailServerApi() {
	}

	public static void createNewMasterDetailModel(ServerDolphin dolphin, String id, String type, Supplier<DTO> dtoSupplier) {

		createCurrentPM(dolphin, id, type, dtoSupplier);
		createMetaPM(dolphin, id);
		addCurrentPMIdHandler(dolphin, id);
	}

	private static void createCurrentPM(ServerDolphin dolphin, String id, String type, Supplier<DTO> dtoSupplier) {
		dolphin.presentationModel(getCurrentPmId(id), GeneralApi.getTechnicalType(type), dtoSupplier.get());
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
