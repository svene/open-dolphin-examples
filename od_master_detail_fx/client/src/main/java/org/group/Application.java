package org.group;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.opendolphin.core.client.ClientDolphin;
import org.svenehrke.javafxextensions.fxml.FXMLLoader2;
import org.svenehrke.javafxextensions.fxml.ViewAndRoot;

import static org.group.ApplicationConstants.*;

public class Application extends javafx.application.Application {
    static ClientDolphin clientDolphin;

    private Button button;
    private TextField nameTextField;
    private Label greetingLabel;

    @Override
    public void start(Stage stage) throws Exception {

        final ViewAndRoot<MainContentView, AnchorPane> main = FXMLLoader2.loadFXML("/mainContent.fxml");

        stage.setTitle("Application Title");

        Scene scene = new Scene(main.getRoot());
        stage.setScene(scene);
        stage.show();

        initializePMs(clientDolphin, main.getView());

        stage.show();
    }

    private void initializePMs(ClientDolphin clientDolphin, MainContentView view) {
		MainContentViewBinder mainContentViewBinder = new MainContentViewBinder();
		mainContentViewBinder.bindView(clientDolphin, view);
		Application.clientDolphin.send(COMMAND_INIT);
    }

}
