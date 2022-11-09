package com.example;

import java.io.IOException;

import javafx.scene.control.TreeItem;

public class AdminController {
    private AppSingleton app;
    AdminController() {
        app = AppSingleton.getInstance();
    }

    public void openSecondary() throws IOException { //Opens new user view window.
        TwitterDriver.openWindow();
    }

    public void addUser(String input) {
        TreeItem<Users> item;
        Users tempUser = new UserPeople();
        tempUser.setId(input);
        item = new TreeItem<Users> (tempUser);   
        app.getCurrGroup().getChildren().add(item);
    }

    public void addGroup(String input) {
        TreeItem<Users> item;
        Users tempUser = new UserGroups();
        tempUser.setId(input);
        item = new TreeItem<Users> (tempUser);   
        app.getCurrGroup().getChildren().add(item);
    }

}
