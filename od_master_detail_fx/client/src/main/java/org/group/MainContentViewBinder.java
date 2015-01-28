package org.group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.opendolphin.binding.JFXBinder;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.examplepms.person.PersonApi;
import org.opendolphinx.client.misc.ClientAttributeStringWrapper;
import org.opendolphinx.client.misc.ModelStoreBinder;
import org.opendolphinx.client.pattern.masterdetail.MasterDetailClientApi;

public class MainContentViewBinder {

	public void bindView(ClientDolphin clientDolphin, MainContentView view) {
		// Initialize the person table:

		ObservableList<PresentationModel> personPMs = FXCollections.observableArrayList();
		new ModelStoreBinder(clientDolphin).bind(personPMs, PersonApi.PERSON_TYPE); // todo: should these be provided via 'MasterDetailClientApi' ?

		view.personTable.setItems(personPMs);

		view.firstNameColumn.setCellValueFactory(cellData -> new ClientAttributeStringWrapper((ClientAttribute) cellData.getValue().getAt(PersonApi.ATT_FIRST_NAME)));
		view.lastNameColumn.setCellValueFactory(cellData -> new ClientAttributeStringWrapper((ClientAttribute) cellData.getValue().getAt(PersonApi.ATT_LAST_NAME)));


	}


	public void handleInitializedPMs(ClientDolphin clientDolphin, MainContentView view) {
		MasterDetailClientApi masterDetail = new MasterDetailClientApi(clientDolphin, PersonApi.PM_MASTER_DETAIL_PERSON_ID, PersonApi.PERSON_TYPE);
		PresentationModel pm0 = masterDetail.findAllPresentationModels().get(0);
		masterDetail.setCurrentPMId(pm0.getId());
		JFXBinder.bind(PersonApi.ATT_FIRST_NAME).of(masterDetail.getCurrentItem()).to("text").of(view.firstNameTextField);
		JFXBinder.bind("text").of(view.firstNameTextField).to(PersonApi.ATT_FIRST_NAME).of(masterDetail.getCurrentItem());
	}
}
