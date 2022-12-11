package com.example;

public class FindLastUpdatedVisitor implements Visitor {

    private String lastUpdatedUser;
    private long lastUpdatedTime = 0;

    @Override
    public void visitUser(Users person) {
        if (person.getLastUpdateTime() > lastUpdatedTime) {
            lastUpdatedTime = person.getLastUpdateTime();
            lastUpdatedUser = person.getId();
        }
    }

    @Override
    public void visitGroup(Groups group) {
        //Do Nothing
    }
    
    String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
}
