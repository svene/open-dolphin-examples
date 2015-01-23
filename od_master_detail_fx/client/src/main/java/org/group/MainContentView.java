package org.group;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.opendolphin.core.PresentationModel;

public class MainContentView {

	@FXML
	TableView<PresentationModel> personTable;

	@FXML
	TableColumn<PresentationModel, String> firstNameColumn;
	@FXML
	TableColumn<PresentationModel, String> lastNameColumn;

	@FXML
	TextField firstNameTextField;

	@FXML
	TextField lastNameTextField;

	@FXML
	TextField birthdayTextField;
}
