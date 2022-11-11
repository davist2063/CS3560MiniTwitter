package com.example;

public class UserCountVisitor implements Visitor {
    private int count = 0;
    @Override
    public void visitUser(Users person) {
        setCount(getCount() + 1);
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
