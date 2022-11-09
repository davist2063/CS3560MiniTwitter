package com.example;

public class UserGroups implements Users {

    private String groupId = "";

    public UserGroups() {
    }

    @Override
    public String getId() {
        return groupId;
    }
    @Override
    public void setId(String string) {
        groupId = string;
    }
    @Override
    public String toString() {
        return this.groupId;
    }
}
