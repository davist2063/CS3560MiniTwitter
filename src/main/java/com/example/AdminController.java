package com.example;

import java.io.IOException;
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AdminController {
    private AppSingleton app;
    private static SceneMaker userSceneMaker;
    private static Scene scene;
    private HashMap<String, Users> userList;
    
    AdminController() {
        app = AppSingleton.getInstance();
        userList = app.getUserList();
    }

    private boolean isIDValid(String id) {
        boolean isValid = true;
        if(!isIDUnique(id)) {
            isValid = false;
        }
        if(!isIDEmpty(id)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isIDUnique(String id) {
        boolean isUnique = true;
        if(userList.containsKey(id)) {
            isUnique = false;
            System.out.println("ID Already Exists!");
        }
        return isUnique;
    }

    private boolean isIDEmpty(String id) {
        boolean isEmpty = true;
        if(id.length() == 0) {
            isEmpty = false;
            System.out.println("ID can not be empty!");
        }
        return isEmpty;
    }

    public void openSecondary(TreeItem<Users> userData) throws IOException { //Opens new user view window.
        if(userData.getValue() instanceof UserPeople) {
            UserController uc = new UserController(userData);
            userSceneMaker = new UserScene(uc);
            Stage stage2 = new Stage();
            stage2.setTitle("Mini Twitter: User View");
            scene = userSceneMaker.createScene();
            stage2.setScene(scene);
            TwitterDriver.linkOwner(stage2);
            stage2.show();
            stage2.setMaxWidth(700);
            stage2.setMinHeight(480);
        }
        else {
            System.out.println("Cannot open user view for a group!");
        }
    }

    public void addUser(String input) {
        if(isIDValid(input)) {
            TreeItem<Users> item;
            Users tempUser = new UserPeople();
            tempUser.setId(input);
            item = new TreeItem<Users>(tempUser, new Circle(8, Color.SEAGREEN));   
            app.getCurrGroup().getChildren().add(item);
            userList.put(input, tempUser);
        }
    }

    public void addGroup(String input) {
        if(isIDValid(input)) {
            TreeItem<Users> item;
            Users tempUser = new UserGroups();
            tempUser.setId(input);
            item = new TreeItem<Users>(tempUser, new Rectangle(16, 16, Color.POWDERBLUE));   
            app.getCurrGroup().getChildren().add(item);
            userList.put(input, tempUser);
        }
    }

}
