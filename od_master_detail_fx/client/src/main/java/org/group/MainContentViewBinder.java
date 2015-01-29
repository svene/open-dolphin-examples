package org.group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
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

		// Bind selection change of 'view.personTable' to currentPmId: // todo: add reverse binding
		MasterDetailClientApi masterDetail = getMasterDetailClientApi(clientDolphin);
		view.personTable.getSelectionModel().selectedItemProperty().addListener((s, o, n) -> {
			if (n != null) {
				masterDetail.setCurrentPMId(n.getId());
			}
		});

		view.firstNameColumn.setCellValueFactory(cellData -> new ClientAttributeStringWrapper((ClientAttribute) cellData.getValue().getAt(PersonApi.ATT_FIRST_NAME)));
		view.lastNameColumn.setCellValueFactory(cellData -> new ClientAttributeStringWrapper((ClientAttribute) cellData.getValue().getAt(PersonApi.ATT_LAST_NAME)));


	}


	public void handleInitializedPMs(ClientDolphin clientDolphin, MainContentView view) {
		MasterDetailClientApi masterDetail = getMasterDetailClientApi(clientDolphin);
		PresentationModel pm0 = masterDetail.findAllPresentationModels().get(0);
		masterDetail.setCurrentPMId(pm0.getId());

		bindTextfield(masterDetail.getCurrentItem(), PersonApi.ATT_FIRST_NAME, view.firstNameTextField);
		bindTextfield(masterDetail.getCurrentItem(), PersonApi.ATT_LAST_NAME, view.lastNameTextField);
		bindTextfield(masterDetail.getCurrentItem(), PersonApi.ATT_BIRTHDAY, view.birthdayTextField);

	}

	private void bindTextfield(PresentationModel currentItem, String attribute, TextField textField) {
		JFXBinder.bind(attribute).of(currentItem).to("text").of(textField);
		JFXBinder.bind("text").of(textField).to(attribute).of(currentItem);
	}

	private MasterDetailClientApi getMasterDetailClientApi(ClientDolphin clientDolphin) {
		return new MasterDetailClientApi(clientDolphin, PersonApi.PM_MASTER_DETAIL_PERSON_ID, PersonApi.PERSON_TYPE);
	}
}
