package com.example;

import java.io.IOException;

public class AdminController {
    AdminController() {
    }

    public void openSecondary() throws IOException { //Opens new user view window.
        TwitterDriver.openWindow();
    }
}
