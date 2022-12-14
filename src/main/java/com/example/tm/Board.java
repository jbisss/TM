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

    public void addTask(String title, int storyPoints, String priority, String username, String epicName) {
        //Task newTask = new Task(title, storyPoints, priority, this.getEpicByName(epicName), this.getUserByUsername(username), time);
        //tasks.add(newTask);
        //System.out.println("Task #" + newTask.getId() + " created.");
    }

    public static void deleteTask(int id) {
        if (tasks.removeIf(t -> t.getId() == id)) {
            System.out.println("Deleted task #" + id);
        }
    }

    public Task getTaskById(int id) {
        List<Task> filtered = tasks.stream().filter(t -> t.getId() == id).toList();;
        return filtered.get(0);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addUser(String name) {
        users.add(new User(name));
    }

    public static User getUserByUsername(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addEpic(String name, String color) {
        epics.add(new Epic(name, color));
    }

    public ArrayList<Epic> getEpics() {
        return epics;
    }

    public static Epic getEpicByName(String name) {
        for (Epic epic : epics) {
            if (epic.getName().equals(name)) {
                return epic;
            }
        }
        return null;
    }

    public void showBoard() {
        System.out.println("\n-------------- ");
        System.out.println("Total tasks on board: " + tasks.size());

        printTasks(tasks);
    }

    public void showBoardByUsername(String username) {
        User user = this.getUserByUsername(username);
        System.out.println("\n-------------- ");

        List<Task> filtered =
                tasks.stream().filter(t -> t.getUserName().equals(username)).toList();

        System.out.println("Total tasks on " + username + " : " + filtered.size());

        printTasks(filtered);
    }

    private void printTasks(List<Task> filtered) {
        for (Task task : filtered) {
            System.out.println("-------------- ");
            System.out.println("-| ID: " + task.getId());
            System.out.println("-| Task: " + task.getTitle());
            System.out.println("-| Epic: " + task.getEpicName());
            System.out.println("-| User: " + task.getUserName());
            System.out.println("-| SP: " + task.getStoryPoints());
            System.out.println("-| Priority: " + task.getPriority());
        }
        System.out.println("-------------- \n");
    }

    public void showBoardByEpicName(String epicname) {
        Epic epic = this.getEpicByName(epicname);
        System.out.println("\n-------------- ");

        List<Task> filtered =
                tasks.stream().filter(t -> t.getEpicName().equals(epicname)).toList();

        System.out.println("Epic " + epicname + " tasks : " + filtered.size());

        printTasks(filtered);
    }
}
