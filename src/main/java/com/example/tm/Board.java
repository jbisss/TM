package com.example.tm;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final String name;
    public static  ArrayList<Task> tasks = new ArrayList<>();
    public static  ArrayList<Epic> epics = new ArrayList<>();
    public static  ArrayList<User> users = new ArrayList<>();

    public Board(String name) {
        this.name = name;
    }


    public static void deleteTask(int id) {
        if (tasks.removeIf(t -> t.getId() == id)) {
            System.out.println("Deleted task #" + id);
        }
    }


    public static User getUserByUsername(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }


    public void addEpic(String name, String color) {
        epics.add(new Epic(name, color));
    }


    public static Epic getEpicByName(String name) {
        for (Epic epic : epics) {
            if (epic.getName().equals(name)) {
                return epic;
            }
        }
        return null;
    }

}
