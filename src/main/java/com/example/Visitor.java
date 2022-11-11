package com.example;

public interface Visitor {
    public void visitUser(Users person);
    public void visitGroup(Groups group);
}
