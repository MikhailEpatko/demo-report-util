package ru.epatko.reportutil.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import ru.epatko.reportutil.model.Question;
import ru.epatko.reportutil.utils.RootDefines;

import static ru.epatko.reportutil.utils.RootDefines.QUESTION_SERVICE;

public class MainController {

    @FXML
    private Button btnDownload;
    @FXML
    private Button btnSchedule;
    @FXML
    private Button btnLogin;

    public void showDownloadWindow(ActionEvent actionEvent) {
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        scene.setRoot(RootDefines.rootDownload);
        ObservableList<Question> questions = QUESTION_SERVICE.getAllGrantedQuestions(actionEvent);
        ListView<Question> selectReport = (ListView<Question>) scene.lookup("#downloadSelectReport");
        selectReport.getItems().addAll(questions);
        selectReport.setStyle("-fx-font: 16px \"Ubuntu\"");
        ListView<String> selectFormat = (ListView<String>) scene.lookup("#downloadSelectFormat");
        selectFormat.setStyle("-fx-font: 16px \"Ubuntu\"");
    }

    public void showSchedulerWindow(ActionEvent actionEvent) {
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        scene.setRoot(RootDefines.rootScheduler);
        ObservableList<Question> questions = QUESTION_SERVICE.getAllGrantedQuestions(actionEvent);
        ListView<Question> selectReport = (ListView<Question>) scene.lookup("#scheduleSelectReport");
        selectReport.getItems().addAll(questions);
        selectReport.setStyle("-fx-font: 16px \"Ubuntu\"");
        ListView<String> selectFormat = (ListView<String>) scene.lookup("#scheduleSelectFormat");
        selectFormat.setStyle("-fx-font: 16px \"Ubuntu\"");
    }

    public void logOut(ActionEvent actionEvent) {
        QUESTION_SERVICE.clearQuestions();
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        scene.setRoot(RootDefines.rootLogin);
    }
}
