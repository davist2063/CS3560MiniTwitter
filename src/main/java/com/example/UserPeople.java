package com.example;

import java.util.HashMap;

import javafx.scene.control.ListView;

public class UserPeople implements Users, Subscribers, Publishers{
    private String userId = "";
    HashMap<String, UserPeople> followers = new HashMap<String, UserPeople>();
    private ListView<UserPeople> currentFollowing;
    private ListView<String> newsFeed;

    public UserPeople() {
        followers.put(this.getId(), this);
        currentFollowing = new ListView<>();
        newsFeed = new ListView<>();
    }
    
    @Override
    public String getId() {
        return userId;
    }
    @Override
    public void setId(String string) {
        userId = string;
        return;
    }
    @Override
    public String toString() {
        return this.userId;
    }
    public HashMap<String, UserPeople> getFollowers() {
        return followers;
    }
    public void subscribe(UserPeople user) { //Adds followers for a given user.
        followers.put(user.getId(), user);
        System.out.println("Added " + user.getId() + " to " + this.getId() + "'s followers list");
        currentFollowing.getItems().add(user);
        return;
    }
    public void updateFeed(String message) {
        newsFeed.getItems().add(message);
        return;
    }
    public void notifyFollowers(String message) {
        for(HashMap.Entry<String, UserPeople> entry : followers.entrySet()) {
            UserPeople user = entry.getValue();
            user.updateFeed(message);
        }
        return;
    }
    public void post(String message) {
        notifyFollowers(message);
        System.out.println("Message Posted Successfully");
        return;
    }
    public ListView<UserPeople> getCurrentFollowers() {
        return currentFollowing;
    }
    public ListView<String> getNewsFeed() {
        return newsFeed;
    }
}
