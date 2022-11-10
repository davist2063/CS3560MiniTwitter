package com.example;

import java.util.HashMap;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AppSingleton {
    public static AppSingleton app;
    TreeView<Users> tree;
    TreeItem<Users> currRoot;
    TreeItem<Users> currGroup;
    private HashMap<String, Users> userList = new HashMap<String, Users>();

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
        Users node = new UserGroups();
        node.setId("Root");
        TreeItem<Users> rootItem = new TreeItem<Users>(node, new Rectangle(16, 16, Color.MIDNIGHTBLUE));
        currRoot = rootItem;
        currGroup = rootItem;
        rootItem.setExpanded(true);
        this.tree = new TreeView<Users>(rootItem); //Attaches the Root to the Base of the TreeView.  

        //Works on the process of node selection
        tree.getSelectionModel()
        .selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
            currRoot = newValue;
            if(newValue.getValue() instanceof UserPeople) {
                currGroup = newValue.getParent();
            }
            else if (newValue.getValue() instanceof UserGroups) {
                currGroup = newValue;
            }
        });
        return;
    }
    TreeView<Users> getTree() {
        return tree;
    }
    void setCurrRoot(TreeItem<Users> newRoot) {
        currRoot = newRoot;
    }
    TreeItem<Users> getCurrRoot() {
        return currRoot;
    }
    void setCurrGroup(TreeItem<Users> newGroup) {
        currGroup = newGroup;
    }
    TreeItem<Users> getCurrGroup() {
        return currGroup;
    }
    HashMap<String, Users> getUserList() {
        return userList;
    }
}
