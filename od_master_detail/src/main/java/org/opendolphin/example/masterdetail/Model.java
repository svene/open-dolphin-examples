package org.opendolphin.example.masterdetail;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.Slot;

import java.util.function.Supplier;

import static org.opendolphin.example.masterdetail.ApplicationConstants.*;

public class Model { // todo: rename as MasterDetailModel

	private final String masterDetailId;
	public final PresentationModel currentItem;
	private StringProperty currentPMid = new SimpleStringProperty();

	public Model(ServerDolphin dolphin, String masterDetailId, String type, Supplier<DTO> dtoSupplier) {
		this.masterDetailId = masterDetailId;
		currentItem = dolphin.presentationModel(MASTER_DETAIL_FOR_ITEMS.currentPMId, type + TECHNICAL_ID_POSTFIX, dtoSupplier.get() );
		PresentationModel metaPM = dolphin.presentationModel(MASTER_DETAIL_FOR_ITEMS.metaPMId, MasterDetailsApi.TYPE, new DTO( new Slot(MasterDetailsApi.ATT_CURRENT_PM_ID, null) ) );

		metaPM.getAt(MasterDetailsApi.ATT_CURRENT_PM_ID).addPropertyChangeListener(evt -> {
			if ( ! (evt.getNewValue() instanceof String)) return;
			String pmId = (String) evt.getNewValue();
			ServerPresentationModel pm = dolphin.findPresentationModelById(pmId);
			if (pm != null) {
				currentItem.getAt(ItemApi.ATT_NAME).setValue(pm.getAt(ItemApi.ATT_NAME).getValue());
			}
		});
	}
}
