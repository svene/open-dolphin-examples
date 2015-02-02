package org.group;

import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.examplepms.person.PersonApi;
import org.opendolphinx.client.misc.ClientAttributeStringWrapper;
import org.opendolphinx.client.pattern.masterdetail.MasterDetailClientApi;
import org.opendolphinx.client.pattern.masterdetail.TableViewBinder;
import org.opendolphinx.extension.javafxclient.TextFieldBinder;

public class MainContentViewBinder {

	public void bindView(ClientDolphin clientDolphin, MainContentView view) {

		MasterDetailClientApi masterDetail = new MasterDetailClientApi(clientDolphin, PersonApi.PM_MASTER_DETAIL_PERSON_ID, PersonApi.PERSON_TYPE);
		masterDetail.addInitializedHandler(() -> handleInitializedPMs(view, masterDetail));
	}

	public void handleInitializedPMs(MainContentView view, MasterDetailClientApi masterDetail) {

		TableViewBinder.bindTableToMasterDetail(view.personTable, masterDetail);

		view.firstNameColumn.setCellValueFactory(cellData -> new ClientAttributeStringWrapper((ClientAttribute) cellData.getValue().getAt(PersonApi.ATT_FIRST_NAME)));
		view.lastNameColumn.setCellValueFactory(cellData -> new ClientAttributeStringWrapper((ClientAttribute) cellData.getValue().getAt(PersonApi.ATT_LAST_NAME)));

		TextFieldBinder.bindTextfield(masterDetail.getCurrentItem(), PersonApi.ATT_FIRST_NAME, view.firstNameTextField);
		TextFieldBinder.bindTextfield(masterDetail.getCurrentItem(), PersonApi.ATT_LAST_NAME, view.lastNameTextField);
		TextFieldBinder.bindTextfield(masterDetail.getCurrentItem(), PersonApi.ATT_BIRTHDAY, view.birthdayTextField);
	}

}
