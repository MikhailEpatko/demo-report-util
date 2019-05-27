package ru.epatko.reportutil.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class MessageController {

    @FXML
    private Text txtError;


    public void showMessage(ActionEvent actionEvent, String errorMessage) {
        try {
            Parent rootError = FXMLLoader.load(Objects.requireNonNull(MessageController.class.getClassLoader().getResource("fxml/message.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Message");
            stage.setMinHeight(200);
            stage.setMinWidth(455);
            stage.setResizable(false);
            stage.setScene(new Scene(rootError));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            ((Text) rootError.getChildrenUnmodifiable().get(0)).setText(errorMessage);
            stage.show();
        } catch (Exception e) {
            log.error("Got exception: ", e);
        }
    }
}
