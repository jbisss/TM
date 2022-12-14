package com.example.tm;

import java.util.ArrayList;

public class User {
    public static ArrayList<String> names = new ArrayList<>();
    public static User currentUser;
    private String username;
    public User(String username) {
        this.username = username;
        names.add(this.username);
    }

    public String getUsername() {
        return username;
    }

}
