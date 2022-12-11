package com.example;

import java.util.HashMap;

import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;

public class UserController {
    private AppSingleton app;
    TreeItem<SysEntries> user;
    Users userData;
    private HashMap<String, SysEntries> userList;

    public UserController(TreeItem<SysEntries> user) {
        this.app = AppSingleton.getInstance();
        this.userList = app.getUserList();
        this.user = user;
        userData = (Users)user.getValue();
    }
    public String getUserId() {
        return user.getValue().getId();
    }
    public Users getUser() {
        return (Users)user.getValue();
    }
    public ListView<Users> getCurrentFollowings() {
        return userData.getCurrentFollowings();
    }
    public ListView<String> getNewsFeed() {
        return userData.getNewsFeed();
    }
    public Users findUser(String userId) {
        SysEntries temp;
        Users user = null;
        if(userList.containsKey(userId)) { //Checks for Existing Users
            temp = userList.get(userId);
            if(temp instanceof Users) { //Checks for Users
                user = (Users)temp;
                if(user.getFollowers().containsKey(userData.getId())) { //Checks if the User has alredy been followed
                    user = null;
                    System.out.println("Can't follow the same user twice");
                }
            }
            else {
                System.out.println("Can't follow a group");
            }
        }
        else {
            System.out.println("Can't Find User");
        }
        if(userData.getId().equals(userId)) { //Prevents user from following themselves.
            user = null;
            System.out.println("Can't follow yourself");
        }
        return user;
    }
    public void followUser(String userId) {
        Users userToFollow = findUser(userId);
        if(userToFollow != null) {
            Users currUser = (Users)user.getValue();
            userToFollow.subscribe(currUser); //Adds current user to the followers list of the userToFollow.
            currUser.getFollowing().put(userId, userToFollow); //Adds userToFollow to the current user's following list.
        }
        else {
            System.out.println("Follow Unsuccessful");
        }
    }
    public void post(String newMessage) {
        //Modify Message 
        newMessage = userData.getId() + ": " + newMessage;
        userData.post(newMessage);
    }
}