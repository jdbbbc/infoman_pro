package com.project.infoman_pro;

import com.project.infoman_pro.utility.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("SEASIDE APP LOGIN");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {


        (new Database()).generateUser("jhed", "eww");
        launch();
    }
}