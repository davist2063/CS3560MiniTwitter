package com.example;

import java.util.ArrayList;

public class UserPeople implements Users{
    private String userId = "";
    private ArrayList<UserPeople> followers = new ArrayList<UserPeople>();

    public UserPeople() {}
    
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
    public ArrayList<UserPeople> getFollowers() {
        return followers;
    }
    public void addFollowers() {
        return;
    }
    private void post(String message) {
        return;
    }
}
