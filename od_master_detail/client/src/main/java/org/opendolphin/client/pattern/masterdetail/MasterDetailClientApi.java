package org.opendolphin.client.pattern.masterdetail;

import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientDolphin;

import java.util.List;

import static org.opendolphin.shared.pattern.masterdetail.MasterDetailApi.*;

public class MasterDetailClientApi {

	private final ClientDolphin clientDolphin;
	private final String masterDetailId;
	private final String type;

	public MasterDetailClientApi(ClientDolphin clientDolphin, String id, String type) {
		this.clientDolphin = clientDolphin;
		this.type = type;
		masterDetailId = id;
	}

	public List<PresentationModel> findAllPresentationModels() {
		return clientDolphin.findAllPresentationModelsByType(type);
	}
	public PresentationModel getCurrentItem() {
		return clientDolphin.findPresentationModelById(getCurrentPmId(masterDetailId));
	}

	public void setCurrentPMId(String id) {
		getMetaPM().getAt(ATT_CURRENT_PM_ID).setValue(id);
	}

	public PresentationModel getMetaPM() {
		return clientDolphin.findPresentationModelById(getMetaPmId(masterDetailId));
	}
}
