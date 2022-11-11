package com.example;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ResultDisplayScene implements SceneMaker {

    private String text;
    public ResultDisplayScene(String text) {
        this.text = text;
    }
    @Override
    public Scene createScene() {
        Text currUser = getMessage();
        StackPane window = new StackPane();
        window.getChildren().addAll(currUser);
        Scene scene = new Scene(window, 400, 100);
        return scene;
    }
    private Text getMessage() {
        Text currUser = new Text(text);
        Font font = Font.font("Verdana", 14);
        currUser.setFont(font);
        return currUser;
    }
    
}
