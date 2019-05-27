package ru.epatko.reportutil.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import ru.epatko.reportutil.model.Token;
import ru.epatko.reportutil.utils.RootDefines;

import static ru.epatko.reportutil.utils.RootDefines.TOKEN_SERVICE;

@Slf4j
public class LoginController {

    @FXML
    private Button btnLogin;
    @FXML
    private TextField loginUrl;
    @FXML
    private TextField loginUsername;
    @FXML
    private TextField loginPassword;

    public void makeLogin(ActionEvent actionEvent) {
        String url = "http://host:port/path/oauth/token";
        String username = loginUsername.getCharacters().toString();
        String password = loginPassword.getCharacters().toString();
        Token token = TOKEN_SERVICE.getToken(username, password, url, actionEvent);
         if (token != null && token.getAccessToken() != null) {
             Scene scene = ((Node) actionEvent.getSource()).getScene();
             scene.setRoot(RootDefines.rootMain);
         }
    }
}
