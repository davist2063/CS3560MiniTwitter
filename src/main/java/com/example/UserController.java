package com.example;

import java.util.HashMap;

import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;

public class UserController {
    private AppSingleton app;
    TreeItem<Users> user;
    UserPeople userData;
    private HashMap<String, Users> userList;

    public UserController(TreeItem<Users> user) {
        this.app = AppSingleton.getInstance();
        this.userList = app.getUserList();
        this.user = user;
        userData = (UserPeople)user.getValue();
    }
    public String getUserId() {
        return user.getValue().getId();
    }
    public ListView<UserPeople> getCurrentFollowers() {
        return userData.getCurrentFollowers();
    }
    public ListView<String> getNewsFeed() {
        return userData.getNewsFeed();
    }
    public UserPeople findUser(String userId) {
        Users temp;
        UserPeople user = null;
        if(userList.containsKey(userId)) {
            temp = userList.get(userId);
            if(temp instanceof UserPeople) {
                System.out.println("User Found");
                user = (UserPeople)temp;
                if(user.getFollowers().containsKey(userData.getId())) {
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
        if(userData.getId().equals(userId)) {
            user = null;
            System.out.println("Can't follow yourself");
        }
        return user;
    }
    public void followUser(String userId) {
        UserPeople userToFollow = findUser(userId);
        if(userToFollow != null) {
            userToFollow.subscribe((UserPeople)user.getValue());
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