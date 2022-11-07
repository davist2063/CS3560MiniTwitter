package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class TwitterDriver extends Application {

    private static SceneMaker adminSceneMaker = new AdminScene();
    private static SceneMaker userSceneMaker = new UserScene();
    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Mini Twitter: Admin Control Panel");
        scene = adminSceneMaker.createScene();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMinHeight(480);
        primaryStage.setMaxHeight(480);
        primaryStage.setMinWidth(780);
        primaryStage.setMaxWidth(780);
        stage = primaryStage;
    }

    static void openWindow() throws IOException {
        Stage stage2 = new Stage();
        stage2.setTitle("Mini Twitter: User View");
        scene = userSceneMaker.createScene();
        stage2.setScene(scene);
        stage2.initOwner(stage);
        stage2.show();
        stage2.setMaxWidth(700);
        stage2.setMinHeight(480);
    }

    public static void main(String[] args) {
        launch();
    }

}