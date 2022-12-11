package com.example;

import java.util.ArrayList;

public class Groups implements SysEntries {

    private String groupId = "";
    private ArrayList<SysEntries> entryList = new ArrayList<SysEntries>();
    private Long creationTime;

    public Groups() {
        creationTime = System.currentTimeMillis();
    }

    @Override
    public String getId() {
        return groupId;
    }
    @Override
    public void setId(String string) {
        groupId = string;
    }
    public ArrayList<SysEntries> getEntryList() {
        return entryList;
    }
    public void addEntry(SysEntries entry) {
        entryList.add(entry);
    }
    @Override
    public String toString() {
        return this.groupId;
    }
    @Override
    public void accept(Visitor visitor) {
        for (SysEntries entry : entryList) {
            entry.accept(visitor);
        }
        visitor.visitGroup(this);
    }
}
