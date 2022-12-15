package com.example.tm;

import javafx.scene.paint.Color;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Task {
    private int id;
    private int storyPoints;
    private String priority;
    private String title;
    private String complexity;
    private User user;
    private Epic epic;
    private int timeRes;
    private boolean isOpen = true;
    private String date;
    private String deadLine;
    private static int count = 0;

    public Task(String title, int storyPoints, String priority, Epic epic, String complexity,
                User user, int time, String deadLine, int sp) {
        this.complexity = complexity;
        this.epic = epic;
        this.priority = priority;
        this.user = user;
        this.storyPoints = storyPoints;
        this.title = title;
        this.timeRes = time;
        this.storyPoints = sp;
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        this.date = dateFormat.format(Calendar.getInstance().getTime());
        this.deadLine = deadLine;
        count++;
        this.id = count;
    }
    public boolean isSpoiled(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        this.date = dateFormat.format(Calendar.getInstance().getTime());
        System.out.println(this.date);
        return this.date.compareTo(this.deadLine) > 0;
    }
    public String getComplexity(){
        return this.complexity;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getEpicName() {
        return epic.getName();
    }


    public int getStoryPoints() {
        return storyPoints;
    }

    public String getPriority() {
        return priority;
    }

    public String getUserName() {
        return user.getUsername();
    }
    public Color getColor(){
        return this.epic.getColor();
    }
}
