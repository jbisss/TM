package com.example.tm;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Epic {
    public static ArrayList<String> names = new ArrayList<>();
    private String name;
    private String color;
    public Epic(String name, String color) {
        this.color = color;
        this.name = name;
        names.add(this.name);
    }
    public Color getColor(){
        if (this.color.equals("RED")) {
            return Color.RED;
        }
        if (this.color.equals("BLUE")) {
            return Color.BLUE;
        }
        if (this.color.equals("GREEN")) {
            return Color.GREEN;
        }
        if (this.color.equals("PURPLE")) {
            return Color.PURPLE;
        }
        if (this.color.equals("BROWN")) {
            return Color.BROWN;
        }
        if (this.color.equals("PINK")) {
            return Color.PINK;
        }
        if (this.color.equals("ORANGE")) {
            return Color.ORANGE;
        }
        if (this.color.equals("CYAN")) {
            return Color.CYAN;
        }
        return Color.BLACK;
    }

    public String getName() {
        return name;
    }
}
