package com.example;

import javafx.collections.ObservableList;

public class PositiveMessageCountVisitor implements Visitor {
    
    private int count = 0;
    private PositiveMessageSingleton list = PositiveMessageSingleton.getInstance();

    @Override
    public void visitUser(Users person) {
        ObservableList<String> messages = person.getNewsFeed().getItems();
        for (String each: messages)
        {
            if(list.isMessagePositive(each)) {
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
