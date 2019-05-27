package ru.epatko.reportutil.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.epatko.reportutil.utils.RootDefines;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Report utils");
        primaryStage.setMinHeight(880);
        primaryStage.setMinWidth(455);
        primaryStage.show();
        primaryStage.setScene(new Scene(RootDefines.rootLogin));
    }
}
