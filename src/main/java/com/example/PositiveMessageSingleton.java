package com.example;

import java.util.ArrayList;

public class PositiveMessageSingleton {
    public static PositiveMessageSingleton positiveMessages;
    ArrayList<String> posWordsList = new ArrayList<String>();
    private PositiveMessageSingleton() {
        addWords("happy");
        addWords("nice");
        addWords("great");
        addWords("amazing");
    }
    public static PositiveMessageSingleton getInstance() {
        if (positiveMessages == null) {
            positiveMessages = new PositiveMessageSingleton();
        }
        return positiveMessages;
    }
    public void addWords(String str) {
        posWordsList.add(str.toLowerCase());
    }
    public ArrayList<String> getPosWordsList() {
        return posWordsList;
    }
    public boolean isMessagePositive(String message) {
        boolean isPositive = false;
        String tempString = message.toLowerCase();
        for(int i = 0; i < posWordsList.size(); i++) {
            if(tempString.contains(posWordsList.get(i))) {
                isPositive = true;
            }
        }
        return isPositive;
    }
}
