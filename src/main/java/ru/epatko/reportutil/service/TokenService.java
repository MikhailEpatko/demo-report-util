package ru.epatko.reportutil.service;

//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
import javafx.event.ActionEvent;
import lombok.extern.slf4j.Slf4j;
import ru.epatko.reportutil.model.Token;
import ru.epatko.reportutil.utils.StringUtils;

//import static ru.epatko.reportutil.utils.RootDefines.MESSAGE_CONTROLLER;
//import static ru.epatko.reportutil.utils.RootDefines.TOKEN_REPOSITORY;

@Slf4j
public class TokenService {

    public Token getToken (String username, String password, String url, ActionEvent actionEvent) {
/////Mocked
//        Token token = null;
//        boolean connectionError = false;
//        try {
//            HttpResponse<Token> response = Unirest.post(url)
//                    .field("grant_type", "grant_type")
//                    .field("scope", "scope")
//                    .field("username", username)
//                    .field("password", password)
//                    .field("client_id", "client_id")
//                    .asObject(Token.class);
//            token = response.getBody();
//        } catch (Exception e) {
//            connectionError = true;
//            log.error("Attempt to login with incorrect URL: ", e);
//            MESSAGE_CONTROLLER.showMessage(actionEvent, "Incorrect URL or connection error.");
//        }
//        if (!connectionError && (token == null || token.getAccessToken() == null)) {
//            log.error("Incorrect user data.");
//            MESSAGE_CONTROLLER.showMessage(actionEvent, "Incorrect user data. Try again.");
//        } else if (!connectionError) {
//            log.info("Token was saved.");
//            token.setUrl(url);
//            TOKEN_REPOSITORY.save(token);
//        }
//        return token;
////----- Start mock -------------------
        Token token = new Token();
        token.setAccessToken("fc88aabb-0f96-48a6-b05e-5edb2f5e1a06");
        token.setRefreshToken("fc88aabb-0f96-48a6-b05e-5edb2f5e1a06");
        token.setTokenType("bearer");
        token.setUrl(url);
////----- End mock -------------------
        return !StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) ? token : null;
    }

    public Token refreshToken(ActionEvent actionEvent) {
/////Mocked
//        Token oldToken = TOKEN_REPOSITORY.find();
//        Token newToken = null;
//        try {
//            HttpResponse<Token> response = Unirest.post(oldToken.getUrl())
//                    .field("grant_type", "grant_type")
//                    .field("scope", "scope")
//                    .field("refresh_token", oldToken.getRefreshToken())
//                    .field("client_id", "client_id")
//                    .asObject(Token.class);
//            newToken = response.getBody();
//            newToken.setUrl(oldToken.getUrl());
//            TOKEN_REPOSITORY.save(newToken);
//        } catch (Exception e) {
//            log.error("Got exception: ", e);
//            MESSAGE_CONTROLLER.showMessage(actionEvent, e.getMessage());
//        }
//        return newToken;
////----- Start mock -------------------
        Token token = new Token();
        token.setAccessToken("fc88aabb-0f96-48a6-b05e-5edb2f5e1a06");
        token.setRefreshToken("fc88aabb-0f96-48a6-b05e-5edb2f5e1a06");
        token.setTokenType("bearer");
        token.setUrl("");
////----- End mock -------------------
        return token;
    }
}
