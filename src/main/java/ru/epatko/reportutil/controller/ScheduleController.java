package ru.epatko.reportutil.controller;

//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;
import ru.epatko.reportutil.model.Question;
import ru.epatko.reportutil.model.Task;
import ru.epatko.reportutil.model.Token;
import ru.epatko.reportutil.utils.RootDefines;
import ru.epatko.reportutil.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.epatko.reportutil.utils.RootDefines.MESSAGE_CONTROLLER;
import static ru.epatko.reportutil.utils.RootDefines.TOKEN_SERVICE;

@Slf4j
public class ScheduleController {

    @FXML
    private TextField recipients;
    @FXML
    private ListView<String> scheduleSelectFormat;
    @FXML
    private ListView<Question> scheduleSelectReport;
    @FXML
    private Button btnSchedule;
    @FXML
    private Button btnBack;
    @FXML
    private TextField minute;
    @FXML
    private TextField hour;
    @FXML
    private TextField dayOfMonth;
    @FXML
    private TextField month;
    @FXML
    private CheckBox dayOfWeek1;
    @FXML
    private CheckBox dayOfWeek2;
    @FXML
    private CheckBox dayOfWeek3;
    @FXML
    private CheckBox dayOfWeek4;
    @FXML
    private CheckBox dayOfWeek5;

    private List<CheckBox> daysOfWeek;

    public void showMainWindow(ActionEvent actionEvent) {
        scheduleSelectReport.getItems().clear();
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        scene.setRoot(RootDefines.rootMain);
    }

    public void createTask(ActionEvent actionEvent) {
        Task task = createTaskObject();
        if (task.getQuestionIds().size() == 0) {
            String message = "There are no selected questions.";
            log.info(message);
            MESSAGE_CONTROLLER.showMessage(actionEvent, message);
        } else {
            Token token = TOKEN_SERVICE.refreshToken(actionEvent);
            if (token == null || token.getAccessToken() == null) {
                String message = "Can't refresh token.";
                log.error(message);
                MESSAGE_CONTROLLER.showMessage(actionEvent, message);
            } else {
//// Mocked:
//                try {
//                    HttpResponse<JsonNode> response = Unirest.post("http://host:port/path")
//                            .header("accept", "application/json")
//                            .header("Content-Type", "application/json")
//                            .header("Authorization", "Bearer " + token.getAccessToken())
//                            .body(task)
//                            .asJson();
//                    if (response.getStatus() == 200) {
//                        String message = "Task was created.";
//                        log.info(message);
//                        MESSAGE_CONTROLLER.showMessage(actionEvent, message);
//                    } else {
//                        String message = String.format("Response status: %d. Response headers: %s. Response body: %s",
//                                response.getStatus(), response.getHeaders().toString(), response.getBody().toString());
//                        log.warn(message);
//                        MESSAGE_CONTROLLER.showMessage(actionEvent, "Task was not created.");
//                    }
//                } catch (UnirestException e) {
//                    String message = "The request was failed. ";
//                    log.error(message, e);
//                    MESSAGE_CONTROLLER.showMessage(actionEvent, message + e.getMessage());
//                } catch (Exception e) {
//                    String message = "Got exception: ";
//                    log.error(message, e);
//                    MESSAGE_CONTROLLER.showMessage(actionEvent, message + e.getMessage());
//                }
////----- Start mock -------------------
                String message = "Task was created.";
                log.info(message);
                MESSAGE_CONTROLLER.showMessage(actionEvent, message);
////----- End mock -------------------
            }
        }
    }

    private Task createTaskObject() {
        Task task = new Task();
        task.setQuestionIds(this.scheduleSelectReport.getSelectionModel().getSelectedItems()
                .stream().map(Question::getId).collect(Collectors.toList()));
        task.setReportFormat(this.scheduleSelectFormat.getSelectionModel().getSelectedItem() == null ? "xlsx" :
                scheduleSelectFormat.getSelectionModel().getSelectedItem());
        task.setCronExpression(createCronExpression());
        task.setEmails(this.recipients.getCharacters().toString().split("\\s*,\\s*"));
        return task;
    }

    private String createCronExpression() {
        StringBuilder sb1 = new StringBuilder("0 ");
        String minute = this.minute.getCharacters().toString();
        String hour = this.hour.getCharacters().toString();
        String dayOfMonth = this.dayOfMonth.getCharacters().toString();
        String month = this.month.getCharacters().toString();
        sb1.append(StringUtils.isEmpty(minute) ? 0 : minute).append(' ')
                .append(StringUtils.isEmpty(hour) ? 0 : hour).append(' ')
                .append(StringUtils.isEmpty(dayOfMonth) ? '*' : dayOfMonth).append(' ')
                .append(StringUtils.isEmpty(month) ? '*' : month).append(' ');

        StringBuilder sb2 = new StringBuilder();
        for (CheckBox checkBox : this.daysOfWeek) {
            if (checkBox.isSelected()) {
                sb2.append(checkBox.getText()).append(",");
            }
        }
        if (sb2.length() > 0) {
            sb2.deleteCharAt(sb2.length() - 1);
        } else {
            sb1.append('*');
        }
        sb1.append(sb2.toString());
        return sb1.toString();
    }

    @FXML
    public void initialize() {
        scheduleSelectReport.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        daysOfWeek = Arrays.asList(dayOfWeek1, dayOfWeek2, dayOfWeek3, dayOfWeek4, dayOfWeek5);

    }
}
