package com.example;

import javafx.collections.ObservableList;

public class PositiveMessageCountVisitor implements Visitor {
    
    private int count = 0;
    private PositiveMessageSingleton list = PositiveMessageSingleton.getInstance();

    @Override
    public void visitUser(Users person) {
        ObservableList<String> messages = person.getNewsFeed().getItems();
        for (String each: messages) //Iterates through all the messages for a user.
        {
            if(list.isMessagePositive(each)) { //Only increments if message contains positive keywords
                setCount(getCount() + 1);
            }
        }
    }

    @Override
    public void visitGroup(Groups group) {
        //Do Nothing
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
}
