package org.svenehrke.opendolphin.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.opendolphin.binding.JFXBinder;
import org.opendolphin.core.client.ClientAttribute;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientPresentationModel;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Binds one attribute to two labels.
 * Pressing the button sets the attribute's value to the current time which will then be shown by both labels.
 */
public class App01 extends javafx.application.Application {

	private Button clientSideOnlyButton;
	private Label label, label2;

	private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss.SSS a");

	@Override
	public void start(Stage stage) throws Exception {

		// Read JavaFXParameters:
		ClientDolphin clientDolphin = OdCsoJavaFXApplicationParameters.clientDolphin;

		// Construct client-side-only presentation model:
		ClientPresentationModel pm1 = new ClientPresentationModel(Constants.PM_1, Arrays.asList(new ClientAttribute(Constants.ATTR_1)));
		pm1.setClientSideOnly(true);
		clientDolphin.getModelStore().add(pm1);

		Pane root = setupStage();

		JFXBinder.bind(Constants.ATTR_1).of(pm1).to("text").of(label);
		JFXBinder.bind(Constants.ATTR_1).of(pm1).to("text").of(label2);

		clientSideOnlyButton.setOnAction(evt -> pm1.getAt(Constants.ATTR_1).setValue(getCurrentTime(timeFormatter)));

		pm1.getAt(Constants.ATTR_1).setValue("-");

		Scene scene = new Scene(root, 300, 300, Color.DODGERBLUE);
		stage.setScene(scene);
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
		);
		return pane;
	}


}
