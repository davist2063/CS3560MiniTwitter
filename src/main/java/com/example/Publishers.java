package com.example;

public interface Publishers {
    public void subscribe(Users users);
    public void notifyFollowers(String message);
}
