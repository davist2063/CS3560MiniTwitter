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
        if(!containsSpaces(id)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isIDValid(String id, String altStr) {
        boolean isValid = true;
        if(!isIDUnique(id, "")) {
            isValid = false;
        }
        if(!isIDEmpty(id)) {
            isValid = false;
        }
        if(!containsSpaces(id)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isIDUnique(String id) {
        boolean isUnique = true;
        if(userList.containsKey(id)) {
            isUnique = false;
            createOutputWindow("ID Already Exists!");
        }
        return isUnique;
    }

    //This version of the funciton is called when checking for unique ids. (ID is unique if count <= 1)
    private boolean isIDUnique(String id, String altStr) {
        int count = 0;
        for (HashMap.Entry<String,SysEntries> mapElement : app.getUserList().entrySet()) {
            if(mapElement.getKey() == id)
                count++;
        }
        return count <= 1 ? true : false;
    }

    private boolean isIDEmpty(String id) {
        boolean isEmpty = true;
        if(id.length() == 0) {
            isEmpty = false;
            createOutputWindow("ID can not be empty!");
        }
        return isEmpty;
    }

    private boolean containsSpaces(String id) {
        boolean noContainSpace = true;
        if(id.length() != id.replace(" ", "").length()) {
            noContainSpace = false;
            createOutputWindow("ID can not contain spaces!");
        }
        return noContainSpace;
    }


    //Top Right Button Functionalities
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
    
    //Bottom Right Button Functionalities
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
    public void validateIds() {
        String resultMessage;
        boolean isValidOverall = true;
        
        //Checks if all of the string ids are valid. For loop returns false if an entry is NOT valid.
        for (HashMap.Entry<String,SysEntries> mapElement : app.getUserList().entrySet()) {
            isValidOverall = isValidOverall && isIDValid(mapElement.getKey(), "");
        }

        resultMessage = isValidOverall ? "All Current IDs are Valid" : "Contains Invalid IDs";
        createOutputWindow(resultMessage);
    }

    public void findLastUpdatedUser() {
        String resultMessage;
        FindLastUpdatedVisitor visit = new FindLastUpdatedVisitor();
        SysEntries root = app.getRootItem().getValue();
        root.accept(visit);
        resultMessage = "Last Updated User: " + visit.getLastUpdatedUser();
        createOutputWindow(resultMessage);
    }
    private void createOutputWindow(String message) { //Open window to display results from bottom right buttons.
        userSceneMaker = new ResultDisplayScene(message);
        Stage stage2 = new Stage();
        stage2.setTitle("Results: ");
        scene = userSceneMaker.createScene();
        stage2.setScene(scene);
        TwitterDriver.linkOwner(stage2);
        stage2.show();
    }
}
