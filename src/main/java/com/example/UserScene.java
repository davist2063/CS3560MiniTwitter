package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserScene implements SceneMaker {
    private UserController user;
    public UserScene() {
        this.user = new UserController();
    }
    public Scene createScene() {
        
        //Creating all buttons/textareas/listviews
        TextArea currentUser = new TextArea("Current User: Davis Tong");
        currentUser.setMaxHeight(20);
        currentUser.setEditable(false);
        TextArea uid = new TextArea("");
        uid.setMaxHeight(41);
        uid.setCenterShape(true);
        TextArea tweetMessage = new TextArea("");
        tweetMessage.setMaxHeight(41);
        tweetMessage.setCenterShape(true);
        Button followUser = new Button("Follow User");
        followUser.setMinSize(170, 41);
        followUser.setCenterShape(true);
        Button postTweet = new Button("Post Tweet");
        postTweet.setMinSize(170, 41);
        postTweet.setCenterShape(true);
        ListView<String> currentFollowing = new ListView<>();
        ListView<String> newsFeed = new ListView<>();

        //Setting button functionality
        followUser.setOnAction(event -> {
            System.out.println("Following User: " + uid.getText());
        });
        postTweet.setOnAction(event -> {
            System.out.println("Posting Tweet: " + tweetMessage.getText());
        });

        //Creating the window
        VBox userWindow = new VBox(20);
        userWindow.setAlignment(Pos.CENTER);
        userWindow.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
        HBox topButtons = new HBox(20);
        topButtons.getChildren().addAll(uid, followUser);
        HBox bottomButtons = new HBox(20);
        bottomButtons.getChildren().addAll(tweetMessage, postTweet);        
        userWindow.getChildren().addAll(currentUser, topButtons, currentFollowing, bottomButtons, newsFeed);
        Scene scene = new Scene(userWindow, 640, 480);
        return scene;
    }
}
