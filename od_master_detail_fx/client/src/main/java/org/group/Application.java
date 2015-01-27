package org.group;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientPresentationModel;
import org.opendolphin.core.client.comm.OnFinishedHandlerAdapter;
import org.svenehrke.javafxextensions.fxml.FXMLLoader2;
import org.svenehrke.javafxextensions.fxml.ViewAndRoot;

import static org.group.ApplicationConstants.*;

import java.util.List;

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

        addClientSideAction();
        new MainContentViewBinder().bindView(clientDolphin, main.getView());
        initializePMs();

        stage.show();
    }

    private void initializePMs() {
		clientDolphin.send(COMMAND_INIT);
    }

    private void addClientSideAction() {
/*
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clientDolphin.send(COMMAND_GREET);
            }
        });
*/
    }
}
