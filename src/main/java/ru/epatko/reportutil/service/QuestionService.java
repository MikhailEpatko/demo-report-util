package ru.epatko.reportutil.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import lombok.extern.slf4j.Slf4j;
import ru.epatko.reportutil.model.Question;
import ru.epatko.reportutil.model.Token;
//import java.util.Arrays;

import static ru.epatko.reportutil.utils.RootDefines.MESSAGE_CONTROLLER;
//import static ru.epatko.reportutil.utils.RootDefines.TOKEN_SERVICE;

@Slf4j
public class QuestionService {

    private final ObservableList<Question> questions = FXCollections.observableArrayList();

    public void clearQuestions() {
        this.questions.clear();
    }

    public ObservableList<Question> getAllGrantedQuestions(ActionEvent actionEvent) {
////Mocked
//        if (this.questions.size() == 0) {
//            Token token = TOKEN_SERVICE.refreshToken(actionEvent);
//            HttpResponse<Question[]> response = getQuestionResponse(actionEvent, token);
//            if (response != null && response.getStatus() == 200) {
//                this.questions.addAll(Arrays.asList(response.getBody()));
//            }
//        }
////----- Start mock -------------------
        for (int i = 1; i <= 20; i++) {
            Question question = new Question();
            question.setId(String.valueOf(i));
            question.setName("Question name - " + i);
            questions.add(question);
        }
////----- End mock -------------------
        return this.questions;
    }

    private HttpResponse<Question[]> getQuestionResponse(ActionEvent actionEvent, Token token) {
        try {
            return Unirest.get("http://host:port/path")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token.getAccessToken())
                    .asObject(Question[].class);
        } catch (Exception e) {
            log.error("Got error while trying get questions list: ", e);
            MESSAGE_CONTROLLER.showMessage(actionEvent, "Connection error: " + e.getMessage());
            return null;
        }
    }


}
