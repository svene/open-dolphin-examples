package org.svenehrke.opendolphin.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.opendolphin.binding.JFXBinder;
import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientPresentationModel;
import org.svenehrke.opendolphin.common.ClientSideOnlyDolphin;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * As {@link App02}, in addition it demonstrates how two unrelated attributes (ATTR_1 and ATTR_2 of pm1 resp. pm2)
 * can be connected (stable binding) with each other using qualifiers (QUALIFIER_1).
 */
public class App03 extends javafx.application.Application {

	private Button clientSideOnlyButton;
	private Label label, label2, label3;

	private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss.SSS a");

	@Override
	public void start(Stage stage) throws Exception {
		// Construct client-side-only presentation model with helper method:
		ClientAttribute attr1 = new ClientAttribute(Constants.ATTR_1, null, Constants.QUALIFIER_1);
		ClientPresentationModel pm1 = ClientSideOnlyDolphin.presentationModel(Constants.PM_1, attr1);

		// new Attribute but same qualifier which connects both attributes without explicit binding:
		ClientAttribute attr2 = new ClientAttribute(Constants.ATTR_2, null, Constants.QUALIFIER_1);
		ClientPresentationModel pm2 = ClientSideOnlyDolphin.presentationModel(Constants.PM_2, attr2);

		// new Attribute but different qualifier means it is not connected to previous attributes:
		ClientAttribute attr3 = new ClientAttribute(Constants.ATTR_3, null, Constants.QUALIFIER_2);
		ClientPresentationModel pm3 = ClientSideOnlyDolphin.presentationModel(Constants.PM_3, attr3);

		Pane root = setupStage();

		JFXBinder.bind(Constants.ATTR_1).of(pm1).to("text").of(label);
		JFXBinder.bind(Constants.ATTR_2).of(pm2).to("text").of(label2);
		JFXBinder.bind(Constants.ATTR_3).of(pm3).to("text").of(label3);

		// Button changes only first attribute:
		clientSideOnlyButton.setOnAction(evt -> pm1.getAt(Constants.ATTR_1).setValue(getCurrentTime(timeFormatter)));

		pm1.getAt(Constants.ATTR_1).setValue("-");
		pm3.getAt(Constants.ATTR_3).setValue("-");

		stage.setScene(new Scene(root, 300, 300));
		stage.setTitle(getClass().getSimpleName());
		stage.show();

	}

	private String getCurrentTime(final DateTimeFormatter format) {
		return LocalTime.now().format(format);
	}

	private Pane setupStage() {
		VBox pane = new VBox();
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setSpacing(10);
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(
			clientSideOnlyButton = new Button("get time")
			,label = new Label("")
			,label2 = new Label("")
			,label3 = new Label("-")
		);
		return pane;
	}


}
