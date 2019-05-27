package ru.epatko.reportutil.utils;

import com.google.gson.Gson;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.extern.slf4j.Slf4j;
import ru.epatko.reportutil.controller.MessageController;
import ru.epatko.reportutil.repository.TokenRepository;
import ru.epatko.reportutil.service.QuestionService;
import ru.epatko.reportutil.service.TokenService;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class RootDefines {

    public static Parent rootScheduler;
    public static Parent rootDownload;
    public static Parent rootLogin;
    public static Parent rootMain;

    public static final TokenRepository TOKEN_REPOSITORY = new TokenRepository();
    public static final MessageController MESSAGE_CONTROLLER = new MessageController();
    public static final TokenService TOKEN_SERVICE = new TokenService();
    public static final QuestionService QUESTION_SERVICE = new QuestionService();

    static {
        try {
            rootMain = FXMLLoader.load(Objects.requireNonNull(RootDefines.class.getClassLoader().getResource("fxml/main.fxml")));
            rootLogin = FXMLLoader.load(Objects.requireNonNull(RootDefines.class.getClassLoader().getResource("fxml/login.fxml")));
            rootDownload = FXMLLoader.load(Objects.requireNonNull(RootDefines.class.getClassLoader().getResource("fxml/download.fxml")));
            rootScheduler = FXMLLoader.load(Objects.requireNonNull(RootDefines.class.getClassLoader().getResource("fxml/scheduler.fxml")));

            Unirest.setObjectMapper(new ObjectMapper() {
                final Gson gson = new Gson();
                public String writeValue(Object value) {
                    return gson.toJson(value);
                }
                public <T> T readValue(String value, Class<T> valueType) {
                    return gson.fromJson(value, valueType);
                }
            });
            Unirest.setConcurrency(20, 5);

        } catch (IOException e) {
            log.error("Got exception: ", e);
        }
    }
}
