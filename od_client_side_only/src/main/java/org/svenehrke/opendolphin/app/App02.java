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
import org.opendolphin.core.client.ClientPresentationModel;
import org.svenehrke.opendolphin.common.ClientSideOnlyDolphin;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * As {@link org.svenehrke.opendolphin.app.App01} with the only difference that a helper method
 * {@link ClientSideOnlyDolphin}
 * is used to create the presentation model.
 */
public class App02 extends javafx.application.Application {

	private Button clientSideOnlyButton;
	private Label label, label2;

	private ClientPresentationModel pm1;
	private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss.SSS a");

	@Override
	public void start(Stage stage) throws Exception {
		// Construct client-side-only presentation model with helper method:
		pm1 = ClientSideOnlyDolphin.presentationModel(Constants.PM_1, Constants.ATTR_1);

		Pane root = setupStage();

		JFXBinder.bind(Constants.ATTR_1).of(pm1).to("text").of(label);
		JFXBinder.bind(Constants.ATTR_1).of(pm1).to("text").of(label2);

		clientSideOnlyButton.setOnAction(evt -> pm1.getAt(Constants.ATTR_1).setValue(getCurrentTime(timeFormatter)));

		pm1.getAt(Constants.ATTR_1).setValue("-");

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
		);
		return pane;
	}


}
