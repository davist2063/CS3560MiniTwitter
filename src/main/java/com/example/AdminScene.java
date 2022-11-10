package com.example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminScene implements SceneMaker{
    private AdminController admin;
    private AppSingleton app = AppSingleton.getInstance();
    public AdminScene() {
        this.admin = new AdminController();
    }
    public Scene createScene() {
        HBox adminWindow = new HBox(20);
        adminWindow.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
        VBox treeView = createTreeView();
        VBox functionBox = createFunctionBox();

        adminWindow.getChildren().addAll(treeView, functionBox);
        Scene scene = new Scene(adminWindow, 1000, 440);
        return scene;
    }
    private VBox createTreeView() {    
        VBox root = new VBox(20);
        TreeView<Users> tree = app.getTree();
        root.getChildren().add(tree);
        root.setMinSize(300, 300);
        root.setPrefHeight(400);
        root.setPrefWidth(300);
        return root;
    }
    private VBox createFunctionBox() {

        //Creating all of the buttons/text areas
        TextArea uid = new TextArea("");
        uid.setMaxSize(170, 41);
        uid.setCenterShape(true);
        TextArea gid = new TextArea("");
        gid.setMaxSize(170, 41);
        gid.setCenterShape(true);
        Button addUser = new Button("Add User");
        addUser.setMinSize(170, 41);
        addUser.setCenterShape(true);
        Button addGroup = new Button("Add Group");
        addGroup.setMinSize(170, 41);
        addGroup.setCenterShape(true);
        Button openUserWindow = new Button("Open User View");
        openUserWindow.setMinSize(360, 41);
        openUserWindow.setCenterShape(true);
        Button showUserTotal = new Button("Show User Total");
        showUserTotal.setMinSize(170, 41);
        showUserTotal.setCenterShape(true);
        Button showGroupTotal = new Button("Show Group Total");
        showGroupTotal.setMinSize(170, 41);
        showGroupTotal.setCenterShape(true);
        Button showMessageTotal = new Button("Show Messages Total");
        showMessageTotal.setMinSize(170, 41);
        showMessageTotal.setCenterShape(true);
        Button showPositivePercentage = new Button("Show Positive Percentage");
        showPositivePercentage.setMinSize(170, 41);
        showPositivePercentage.setWrapText(true);
        showPositivePercentage.setCenterShape(true);

        //Setting Button Functionality
        addUser.setOnAction(event -> {
            admin.addUser(uid.getText());
        });
        addGroup.setOnAction(event -> {
            admin.addGroup(gid.getText());
        });
        openUserWindow.setOnAction(event -> {
            try {
                admin.openSecondary(app.getCurrRoot());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        showUserTotal.setOnAction(event -> {
            System.out.println("Showing User Total");
        });
        showGroupTotal.setOnAction(event -> {
            System.out.println("Showing Group Total");
        });
        showMessageTotal.setOnAction(event -> {
            System.out.println("Showing Message Total");
        });
        showPositivePercentage.setOnAction(event -> {
            System.out.println("Showing Positive Percentage");
        });

        //Creating the Right Side UI Panel for the Admin Control Panel
        VBox rightBox = new VBox(20);
        rightBox.setMinSize(400, 400);
        rightBox.setPrefSize(400, 400);
        rightBox.setPadding(new Insets(0.0, 20.0, 20.0, 20.0));
        
        HBox topButtons = new HBox(20);
        VBox topLeft = new VBox(20);
        topLeft.getChildren().addAll(uid, gid);
        VBox topRight = new VBox(20);
        topRight.getChildren().addAll(addUser, addGroup);
        topButtons.getChildren().addAll(topLeft, topRight);

        HBox bottomButtons = new HBox(20);
        bottomButtons.setPadding(new Insets(114.0, 0.0, 0.0, 0.0));
        VBox bottomLeft = new VBox(20);
        bottomLeft.getChildren().addAll(showUserTotal, showMessageTotal);
        VBox bottomRight = new VBox(20);
        bottomRight.getChildren().addAll(showGroupTotal, showPositivePercentage);
        bottomButtons.getChildren().addAll(bottomLeft, bottomRight);

        rightBox.getChildren().addAll(topButtons, openUserWindow, bottomButtons);
        return rightBox;
    }
}
