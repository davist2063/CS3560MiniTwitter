package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class UserScene implements SceneMaker {
    private UserController user;
    public UserScene() {
        this.user = new UserController();
    }
    public Scene createScene() { //XML Alternative for creating secondary.fxml
        VBox testBox = new VBox(20);
        testBox.setAlignment(Pos.CENTER);
        testBox.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
        Button test = new Button("Open User View");
        test.setOnAction(event -> {user.displayMessage();});
        testBox.getChildren().addAll(test);
        Scene scene = new Scene(testBox, 640, 480);
        return scene;
    }
}
