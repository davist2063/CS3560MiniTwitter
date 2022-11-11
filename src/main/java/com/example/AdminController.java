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
    private HashMap<String, SysEntries> userList;
    
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

    public void openSecondary(TreeItem<SysEntries> userData) throws IOException { //Opens new user view window.
        if(userData.getValue() instanceof Users) {
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
            TreeItem<SysEntries> item;
            Groups currGroup = (Groups)app.getCurrGroup().getValue();
            SysEntries tempUser = new Users();
            tempUser.setId(input);
            item = new TreeItem<SysEntries>(tempUser, new Circle(8, Color.SEAGREEN));   
            app.getCurrGroup().getChildren().add(item);
            currGroup.addEntry(tempUser);
            userList.put(input, tempUser);
        }
    }

    public void addGroup(String input) {
        if(isIDValid(input)) {
            TreeItem<SysEntries> item;
            Groups currGroup = (Groups)app.getCurrGroup().getValue();
            SysEntries newGroup = new Groups();
            newGroup.setId(input);
            item = new TreeItem<SysEntries>(newGroup, new Rectangle(16, 16, Color.POWDERBLUE));   
            app.getCurrGroup().getChildren().add(item);
            currGroup.addEntry(newGroup);
            userList.put(input, newGroup);
        }
    }

    public void getGroupCount() {
        String resultMessage;
        GroupCountVisitor visit = new GroupCountVisitor();
        SysEntries root = app.getRootItem().getValue();
        root.accept(visit);
        resultMessage = "Total # Groups: " + visit.getCount();
        createOutputWindow(resultMessage);
    }
    public void getUserCount() {
        String resultMessage;
        UserCountVisitor visit = new UserCountVisitor();
        SysEntries root = app.getRootItem().getValue();
        root.accept(visit);
        resultMessage = "Total # Users: " + visit.getCount();
        createOutputWindow(resultMessage);
    }
    public void getMessageCount() {
        String resultMessage;
        MessageCountVisitor visit = new MessageCountVisitor();
        SysEntries root = app.getRootItem().getValue();
        root.accept(visit);
        resultMessage = "Total # Messages: " + visit.getCount();
        createOutputWindow(resultMessage);
    }
    public void getPositivePercentage() {
        String resultMessage;
        double numMessage;
        double numPositive;
        double percentage;
        MessageCountVisitor mcVisitor = new MessageCountVisitor();
        PositiveMessageCountVisitor pmVisitor = new PositiveMessageCountVisitor();
        SysEntries root = app.getRootItem().getValue();
        root.accept(mcVisitor);
        root.accept(pmVisitor);
        numMessage = mcVisitor.getCount();
        numPositive = pmVisitor.getCount();
        if(numMessage == 0) {
            percentage = 0;
        }
        else {
            percentage = numPositive / numMessage * 100.0;
        }
        resultMessage = "Positive Messages Percentage: " +
         String.format("%5.2f", percentage) + "%";
        createOutputWindow(resultMessage);
    }
    private void createOutputWindow(String message) {
        userSceneMaker = new ResultDisplayScene(message);
        Stage stage2 = new Stage();
        stage2.setTitle("Results: ");
        scene = userSceneMaker.createScene();
        stage2.setScene(scene);
        TwitterDriver.linkOwner(stage2);
        stage2.show();
    }
}
