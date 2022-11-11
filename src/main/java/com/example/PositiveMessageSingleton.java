package com.example;

import java.util.ArrayList;

public class PositiveMessageSingleton {
    public static PositiveMessageSingleton positiveMessages;
    ArrayList<String> posWordsList = new ArrayList<String>();
    private PositiveMessageSingleton() {
        String[] positiveWords = {"happy", "nice", "great", "amazing", "good", "excellent", "hooray"};
        addAllWords(positiveWords);
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
    private void addAllWords(String[] args) {
        for(int i = 0; i < args.length; i++) {
            addWords(args[i]);
        }
    }
    public ArrayList<String> getPosWordsList() {
        return posWordsList;
    }
    public boolean isMessagePositive(String message) { //Checks string for presence of all keywords.
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
