package ru.epatko.reportutil.controller;

//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.DirectoryChooser;
import lombok.extern.slf4j.Slf4j;
import ru.epatko.reportutil.model.Question;
import ru.epatko.reportutil.model.Token;
import ru.epatko.reportutil.utils.RootDefines;

import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.List;
//import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.epatko.reportutil.utils.RootDefines.MESSAGE_CONTROLLER;
import static ru.epatko.reportutil.utils.RootDefines.TOKEN_SERVICE;

@Slf4j
public class DownloadController {

    @FXML
    private Button btnDownload;
    @FXML
    private Button btnBack;
    @FXML
    private ListView<Question> downloadSelectReport;
    @FXML
    private ListView<String> downloadSelectFormat;

    public void showMainWindow(ActionEvent actionEvent) {
        downloadSelectReport.getItems().clear();
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        scene.setRoot(RootDefines.rootMain);
    }

    public void downloadReport(ActionEvent actionEvent) {
        List<String> ids = this.downloadSelectReport.getSelectionModel().getSelectedItems()
                .stream().map(Question::getId).collect(Collectors.toList());
        if (ids.size() == 0) {
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
                String fileFormat = downloadSelectFormat.getSelectionModel().getSelectedItem() == null ? "xlsx" :
                        downloadSelectFormat.getSelectionModel().getSelectedItem();
////----- Start mock -------------------
                String fileName = "report." + fileFormat;
                File fileToZip = new File(getClass().getClassLoader().getResource("files/" + fileName).getFile());
                try {
                    final DirectoryChooser directoryChooser = new DirectoryChooser();
                    configuringDirectoryChooser(directoryChooser);
                    File directory = directoryChooser.showDialog(((Node) actionEvent.getSource()).getScene().getWindow());
                    if (directory != null && directory.getAbsolutePath() != null) {
                        FileOutputStream fos = new FileOutputStream(directory.getAbsolutePath() + File.separator + "reports.zip");
                        ZipOutputStream zipOut = new ZipOutputStream(fos);
                        for (String questionId : ids) {
                            FileInputStream fis = new FileInputStream(fileToZip);
                            ZipEntry zipEntry = new ZipEntry(questionId + ". " + fileName);
                            zipOut.putNextEntry(zipEntry);
                            byte[] bytes = new byte[1024];
                            int length;
                            while ((length = fis.read(bytes)) >= 0) {
                                zipOut.write(bytes, 0, length);
                            }
                            fis.close();
                        }
                        zipOut.close();
                        fos.close();
                    }
                } catch (Exception e) {
                    log.error("Got exception: ", e);
                }

////----- End mock -------------------
//// Mocked:
//                try {
//                    HttpResponse<InputStream> response = Unirest.post("http://host:port/path")
//                            .header("accept", "application/json")
//                            .header("Content-Type", "application/json")
//                            .header("Authorization", "Bearer " + token.getAccessToken())
//                            .queryString("reportFormat", fileFormat)
//                            .body(ids)
//                            .asBinary();
//                    if (response.getStatus() == 200) {
//                        InputStream body = response.getBody();
//                        final DirectoryChooser directoryChooser = new DirectoryChooser();
//                        configuringDirectoryChooser(directoryChooser);
//                        File directory = directoryChooser.showDialog(((Node) actionEvent.getSource()).getScene().getWindow());
//                        if (directory != null) {
//                            byte[] array = new byte[response.getBody().available()];
//                            body.read(array);
//                            Files.write(Paths.get(directory.getAbsolutePath() + File.separator + "reports.zip"), array);
//                        } else {
//                            String message = "File was not saved. Cause: directory was not selected.";
//                            log.warn(message);
//                            MESSAGE_CONTROLLER.showMessage(actionEvent, message);
//                        }
//                    } else {
//                        String message = String.format("Response status: %d. Response headers: %s.", response.getStatus(),
//                                response.getHeaders().toString());
//                        log.warn(message);
//                        MESSAGE_CONTROLLER.showMessage(actionEvent, message);
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
            }
        }

    }

    @FXML
    public void initialize() {
        downloadSelectReport.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        directoryChooser.setTitle("Select directory.");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
}
