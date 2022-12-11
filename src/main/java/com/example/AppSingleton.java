package com.example;

import java.util.HashMap;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AppSingleton {
    public static AppSingleton app;
    TreeView<SysEntries> tree;
    TreeItem<SysEntries> rootItem;
    TreeItem<SysEntries> currRoot;
    TreeItem<SysEntries> currGroup;
    private HashMap<String, SysEntries> userList = new HashMap<String, SysEntries>();

    private AppSingleton() {
        createTree();
    }

    public static AppSingleton getInstance()
    {
        if (app == null) {
            app = new AppSingleton();
        }
        return app;
    }

    private void createTree() {
        //Creating the root node
        SysEntries node = new Groups();
        node.setId("Root");
        this.rootItem = new TreeItem<SysEntries>(node, new Rectangle(16, 16, Color.MIDNIGHTBLUE));
        currRoot = rootItem;
        currGroup = rootItem;
        rootItem.setExpanded(true);
        this.tree = new TreeView<SysEntries>(rootItem); //Attaches the Root to the Base of the TreeView.  
        
        //Works on the process of node selection
        tree.getSelectionModel()
        .selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
            currRoot = newValue;
            if(newValue.getValue() instanceof Users) {
                currGroup = newValue.getParent();
            }
            else if (newValue.getValue() instanceof Groups) {
                currGroup = newValue;
            }
        });

        userList.put("Root", node);
        return;
    }
    TreeView<SysEntries> getTree() {
        return tree;
    }
    void setCurrRoot(TreeItem<SysEntries> newRoot) {
        currRoot = newRoot;
    }
    TreeItem<SysEntries> getCurrRoot() {
        return currRoot;
    }
    void setCurrGroup(TreeItem<SysEntries> newGroup) {
        currGroup = newGroup;
    }
    TreeItem<SysEntries> getCurrGroup() {
        return currGroup;
    }
    HashMap<String, SysEntries> getUserList() {
        return userList;
    }
    public TreeItem<SysEntries> getRootItem() {
        return rootItem;
    }
}
