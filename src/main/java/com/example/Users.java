package com.example;

import java.util.HashMap;

import javafx.scene.control.ListView;

public class Users implements SysEntries, Subscribers, Publishers{
    private String userId = "";
    HashMap<String, Users> followers = new HashMap<String, Users>();
    HashMap<String, Users> following = new HashMap<String, Users>();
    private ListView<Users> currentFollowings;
    private ListView<String> newsFeed;

    public Users() {
        followers.put(this.getId(), this);
        currentFollowings = new ListView<>();
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
    public HashMap<String, Users> getFollowers() {
        return followers;
    }
    public HashMap<String, Users> getFollowing() {
        return following;
    }
    public void subscribe(Users user) { //Adds followers for a given user.
        followers.put(user.getId(), user);
        System.out.println("Added " + this.getId() + " to " + user.getId() + "'s followings list");
        user.getCurrentFollowings().getItems().add(this);
        return;
    }
    public void updateFeed(String message) {
        newsFeed.getItems().add(message);
        return;
    }
    public void notifyFollowers(String message) {
        for(HashMap.Entry<String, Users> entry : followers.entrySet()) {
            Users user = entry.getValue();
            user.updateFeed(message);
        }
        return;
    }
    public void post(String message) {
        notifyFollowers(message);
        return;
    }
    public ListView<Users> getCurrentFollowings() {
        return currentFollowings;
    }
    public ListView<String> getNewsFeed() {
        return newsFeed;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitUser(this);
    }
}
