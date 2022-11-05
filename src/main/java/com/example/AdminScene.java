package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminScene implements SceneMaker{
    private AdminController admin;
    public AdminScene() {
        this.admin = new AdminController();
    }
    public Scene createScene() { //XML Alternative for creating primary.fxml
        VBox testBox = new VBox(20);
        testBox.setAlignment(Pos.TOP_RIGHT);
        testBox.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
        //Pane treeView = new Pane();
        Button addUser = new Button("Add User");
        Button addGroup = new Button("Add Group");
        Button openUserWindow = new Button("Open User View");
        Button showUserTotal = new Button("Show User Total");
        Button showGroupTotal = new Button("Show Group Total");
        Button showMessageTotal = new Button("Show Messages Total");
        Button showPositivePercentage = new Button("Show Positive Percentage");
        openUserWindow.setOnAction(event -> {
            try {
                admin.openSecondary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        testBox.getChildren().addAll(addUser, addGroup, openUserWindow);
        Scene scene = new Scene(testBox, 640, 480);
        return scene;
    }
}
