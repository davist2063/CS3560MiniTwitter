package com.example;
public class GroupCountVisitor implements Visitor{

    private int count = -1;
    @Override
    public void visitUser(Users person) {
        //Do Nothing
    }

    @Override
    public void visitGroup(Groups group) {
        setCount(getCount() + 1);
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
