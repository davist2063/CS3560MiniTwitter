package com.example;

public interface SysEntries {
    public String getId();

    public void setId(String string);

    public void accept(Visitor visitor);
    
    @Override
    public String toString();
}
