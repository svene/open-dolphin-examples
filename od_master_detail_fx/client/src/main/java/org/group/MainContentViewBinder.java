package org.group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.opendolphin.core.PresentationModel;
import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.examplepms.person.PersonApi;
import org.opendolphinx.client.misc.ClientAttributeStringWrapper;
import org.opendolphinx.client.misc.ModelStoreBinder;

public class MainContentViewBinder {

	public void bindView(ClientDolphin clientDolphin, MainContentView view) {
		// Initialize the person table:

		ObservableList<PresentationModel> personPMs = FXCollections.observableArrayList();
		new ModelStoreBinder(clientDolphin).bind(personPMs, PersonApi.PERSON_TYPE);

		view.personTable.setItems(personPMs);

		view.firstNameColumn.setCellValueFactory(cellData -> new ClientAttributeStringWrapper((ClientAttribute) cellData.getValue().getAt(PersonApi.ATT_FIRST_NAME)));
		view.lastNameColumn.setCellValueFactory(cellData -> new ClientAttributeStringWrapper((ClientAttribute) cellData.getValue().getAt(PersonApi.ATT_LAST_NAME)));

	}
}
