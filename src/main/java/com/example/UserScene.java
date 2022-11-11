package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UserScene implements SceneMaker {
    private UserController userControl;
    public UserScene(UserController uc) {
        this.userControl = uc;
    }
    public Scene createScene() {
        
        //Creating all buttons/textareas/listviews
        Font font = Font.font("Verdana", FontWeight.BOLD, 16);
        Font smallerfont = Font.font("Verdana", 12);
        Text currUser = new Text("Current User: " + userControl.getUserId());
        Text currFollowing = new Text("Currently Following: ");
        Text feed = new Text("News Feed: ");
        currUser.setFont(font);
        currFollowing.setFont(smallerfont);
        feed.setFont(smallerfont);
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
        ListView<Users> currentFollowing = userControl.getCurrentFollowings();
        ListView<String> newsFeed = userControl.getNewsFeed();

        //Setting button functionality
        followUser.setOnAction(event -> {
            System.out.println("Following User: " + uid.getText());
            userControl.followUser(uid.getText());
        });
        postTweet.setOnAction(event -> {
            System.out.println("Posting Tweet: " + tweetMessage.getText());
            userControl.post(tweetMessage.getText());
        });

        //Creating the window
        VBox userWindow = new VBox(20);
        userWindow.setAlignment(Pos.CENTER);
        userWindow.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
        HBox topButtons = new HBox(20);
        topButtons.getChildren().addAll(uid, followUser);
        HBox bottomButtons = new HBox(20);
        bottomButtons.getChildren().addAll(tweetMessage, postTweet);        
        userWindow.getChildren().addAll(currUser, topButtons, currFollowing, currentFollowing, bottomButtons, feed, newsFeed);
        Scene scene = new Scene(userWindow, 640, 480);
        return scene;
    }
}
