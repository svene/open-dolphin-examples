package org.opendolphin.example.masterdetail;

import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.Slot;

import java.util.function.Supplier;

import static org.opendolphin.example.masterdetail.ApplicationConstants.*;

public class MasterDetailModel {

	public MasterDetailModel(ServerDolphin dolphin, String id, String type, Supplier<DTO> dtoSupplier) {

		PresentationModel currentItem = dolphin.presentationModel(MasterDetailsApi.getCurrentPmId(id), getTechnicalType(type), dtoSupplier.get() );
		PresentationModel metaPM = dolphin.presentationModel(MasterDetailsApi.getMetaPmId(id), MasterDetailsApi.TYPE, new DTO( new Slot(MasterDetailsApi.ATT_CURRENT_PM_ID, null) ) );

		metaPM.getAt(MasterDetailsApi.ATT_CURRENT_PM_ID).addPropertyChangeListener(evt -> {
			if ( ! (evt.getNewValue() instanceof String)) {
				return;
			}
			String pmId = (String) evt.getNewValue();
			ServerPresentationModel pm = dolphin.findPresentationModelById(pmId);
			if (pm != null) {
				currentItem.getAt(ItemApi.ATT_NAME).setValue(pm.getAt(ItemApi.ATT_NAME).getValue());
			}
		});
	}
}
