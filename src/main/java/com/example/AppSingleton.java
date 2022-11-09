package com.example;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AppSingleton {
    public static AppSingleton app;
    TreeView<Users> tree;
    TreeItem<Users> currRoot;
    TreeItem<Users> currGroup;
    private AppSingleton() {
        createTree();
    }

    public static AppSingleton getInstance()
    {
        if (app == null) {
            System.out.println("Only Generated Once");
            app = new AppSingleton();
        }
        return app;
    }

    private void createTree() {
        System.out.println("Creating Tree");
        //Creating the root node
        Users node = new UserGroups();
        node.setId("Root");
        TreeItem<Users> rootItem = new TreeItem<Users>(node, new Rectangle(16, 16, Color.CORAL));
        currRoot = rootItem;
        currGroup = rootItem;
        rootItem.setExpanded(true);
        this.tree = new TreeView<Users>(rootItem); //Attaches the Root to the Base of the TreeView.

        //Adding Test Leaf Elements (Mixture of users and groups)
        // for (int i = 1; i < 50; i++) {
        //     TreeItem<Users> item;
        //     if(i % 2 == 0) {
        //         Users tempUser = new UserGroups();
        //         tempUser.setId("Message" + i);
        //         item = new TreeItem<Users> (tempUser);   
        //     }
        //     else {
        //         Users tempUser = new UserPeople();
        //         tempUser.setId("Message" + i);
        //         item = new TreeItem<Users> (tempUser);   
        //     }
        //     rootItem.getChildren().add(item);
        // }     

        //Works on the process of node selection
        tree.getSelectionModel()
        .selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
            currRoot = newValue;
            if(newValue.getValue() instanceof UserPeople) {
                currGroup = newValue.getParent();
            }
            else if (newValue.getValue() instanceof UserGroups) {
                System.out.println("Selected a group!");
                currGroup = newValue;
            }

            //Test Comments: Delete later
            System.out.println();
            System.out.println("Selected Group : " + currRoot.getValue());
            System.out.println(currRoot.getValue() + "'s group is " + currGroup.getValue());
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
}
