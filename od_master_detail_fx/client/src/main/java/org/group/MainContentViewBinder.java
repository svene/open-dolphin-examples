package org.group;

import javafx.scene.control.TextField;
import org.opendolphin.binding.JFXBinder;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.examplepms.person.PersonApi;
import org.opendolphinx.client.misc.ClientAttributeStringWrapper;
import org.opendolphinx.client.pattern.masterdetail.MasterDetailClientApi;
import org.opendolphinx.client.pattern.masterdetail.TableViewBinder;
import org.opendolphinx.extension.javafxclient.TextFieldBinder;

public class MainContentViewBinder {

	public void bindView(ClientDolphin clientDolphin, MainContentView view) {

		MasterDetailClientApi masterDetail = getMasterDetailClientApi(clientDolphin);

		TableViewBinder.bindTableToMasterDetail(view.personTable, masterDetail);

		view.firstNameColumn.setCellValueFactory(cellData -> new ClientAttributeStringWrapper((ClientAttribute) cellData.getValue().getAt(PersonApi.ATT_FIRST_NAME)));
		view.lastNameColumn.setCellValueFactory(cellData -> new ClientAttributeStringWrapper((ClientAttribute) cellData.getValue().getAt(PersonApi.ATT_LAST_NAME)));
	}



	public void handleInitializedPMs(ClientDolphin clientDolphin, MainContentView view) {
		MasterDetailClientApi masterDetail = getMasterDetailClientApi(clientDolphin);
		PresentationModel pm0 = masterDetail.findAllPresentationModels().get(0);

		TextFieldBinder.bindTextfield(masterDetail.getCurrentItem(), PersonApi.ATT_FIRST_NAME, view.firstNameTextField);
		TextFieldBinder.bindTextfield(masterDetail.getCurrentItem(), PersonApi.ATT_LAST_NAME, view.lastNameTextField);
		TextFieldBinder.bindTextfield(masterDetail.getCurrentItem(), PersonApi.ATT_BIRTHDAY, view.birthdayTextField);

		TableViewBinder.bindTableToMasterDetail2(view.personTable, masterDetail);

		// Init data: todo (Sven 29.01.15): on server side?
		masterDetail.setCurrentPMId(pm0.getId());
	}

	private MasterDetailClientApi getMasterDetailClientApi(ClientDolphin clientDolphin) {
		return new MasterDetailClientApi(clientDolphin, PersonApi.PM_MASTER_DETAIL_PERSON_ID, PersonApi.PERSON_TYPE);
	}
}
