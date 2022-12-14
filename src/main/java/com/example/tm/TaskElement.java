package com.example.tm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TaskElement {
    public VBox vBox;
    public ComboBox<String> comboUsers;
    public ComboBox<String> comboEpic;
    public Button buttonAddTask;
    public Button buttonExit;
    public Button buttonAddEpic;
    public Label labelLind;
    public TextField fieldTitle;
    public TextField fieldPriority;
    public TextField fieldComplexity;
    public ComboBox<String> comboEpicChoose;
    public Button buttonApprove;
    public Button buttonApproveCombo;
    public ArrayList<VBox> vBoxes = new ArrayList<>();
    public TextField fieldNameEpic;
    public TextField fieldColorEpic;
    public Label labelEpicText;
    public Button buttonApproveEpic;
    public TextField fieldDeadline;

    private int countCurrentTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        char[] time = date.toCharArray();
        int timeH = time[0] + time[1] - 96;
        int timeM = time[3] + time[4] - 96;
        int timeS = time[6] + time[7] - 96;
        return timeH * 3600 + timeM * 60 + timeS;
    }
    public void buttonAddEpicClick(){
        labelEpicText.setVisible(true);
        fieldColorEpic.setVisible(true);
        fieldNameEpic.setVisible(true);
        buttonApproveEpic.setVisible(true);
    }
    public void buttonApproveEpicClick(){
        if(!fieldNameEpic.getText().equals("") && !fieldColorEpic.getText().equals("")){
            labelEpicText.setVisible(false);
            fieldColorEpic.setVisible(false);
            fieldNameEpic.setVisible(false);
            buttonApproveEpic.setVisible(false);
            if(Board.getEpicByName(fieldNameEpic.getText())==null){
                Board.epics.add(new Epic(fieldNameEpic.getText(), fieldColorEpic.getText()));
                ObservableList<String> epicList = FXCollections.observableArrayList(Epic.names);
                comboEpic.setItems(epicList);
            }
            fieldColorEpic.setText("");
            fieldNameEpic.setText("");
        }
    }
    private void clearVBox(){
        vBox.getChildren().removeAll(vBoxes);
        vBoxes.clear();
    }
    private void createElements(Task task){
        VBox vBoxNew = new VBox();
        Label labelId = new Label("#" + task.getId());
        Label labelTitle = new Label("Title: " + task.getTitle());
        Label labelEpic = new Label("Epic: " + task.getEpicName());
        labelEpic.setTextFill(task.getColor());
        Label labelSp = new Label("SP: " + task.getStoryPoints());
        Label labelName = new Label("Username: " + task.getUserName());
        Label labelPriority = new Label("Priority: " + task.getPriority());
        System.out.println(task.isSpoiled());
        String statusSet = task.isSpoiled() ? "Просрочено" : "Не просрочено";
        Label labelStatus = new Label("Status: " + statusSet);
        if (task.isSpoiled()){
            labelStatus.setTextFill(Color.RED);
        } else {
            labelStatus.setTextFill(Color.GREEN);
        }

        Button button = new Button("Удалить");
        button.setOnAction(actionEvent -> {
            Board.deleteTask(task.getId());
            clearVBox();
        });
        Label labelEmpty = new Label();
        labelEmpty.setPrefHeight(20);
        vBoxNew.getChildren().add(labelId);
        vBoxNew.getChildren().add(labelTitle);
        vBoxNew.getChildren().add(labelEpic);
        vBoxNew.getChildren().add(labelSp);
        vBoxNew.getChildren().add(labelName);
        vBoxNew.getChildren().add(labelPriority);
        vBoxNew.getChildren().add(labelStatus);
        vBoxNew.getChildren().add(button);
        vBoxNew.getChildren().add(labelEmpty);
        vBoxes.add(vBoxNew);
        vBox.getChildren().add(vBoxNew);
    }
    public void refreshTasks(User user, String epic){
        clearVBox();
        for (Task task : Board.tasks) {
            if (task.getUserName().equals(user.getUsername()) && task.getEpicName().equals(epic)) {
                createElements(task);
            }
        }
    }
    public void refreshTasks(User user){
        clearVBox();
        for (Task task : Board.tasks) {
            if (task.getUserName().equals(user.getUsername())) {
                createElements(task);
            }
        }
    }
    public void refreshTasks(String epic){
        clearVBox();
        for (Task task : Board.tasks) {
            if (task.getEpicName().equals(epic)) {
                createElements(task);
            }
        }
    }
    public void buttonAddTaskClick(){
        labelLind.setText("Добавить задачу");
        labelLind.setVisible(true);
        fieldComplexity.setVisible(true);
        fieldPriority.setVisible(true);
        fieldTitle.setVisible(true);
        fieldDeadline.setVisible(true);
        comboEpicChoose.setVisible(true);
        buttonApprove.setVisible(true);
        ObservableList<String> epicList = FXCollections.observableArrayList(Epic.names);
        comboEpicChoose.setItems(epicList);
    }
    public void approveButtonComboClick(){
        if (comboUsers.getValue()!=null && comboEpic.getValue() != null){
            refreshTasks(Board.getUserByUsername(comboUsers.getValue()), comboEpic.getValue());
        } else if (comboUsers.getValue()!=null){
            refreshTasks(Board.getUserByUsername(comboUsers.getValue()));
        } else if (comboEpic.getValue() != null){
            refreshTasks(comboEpic.getValue());
        }
    }
    public void approveButtonClick(){
        if (!fieldTitle.getText().equals("") && !fieldComplexity.getText().equals("")
                && !fieldPriority.getText().equals("") && comboEpicChoose.getValue() != null) {
            labelLind.setVisible(false);
            fieldComplexity.setVisible(false);
            fieldPriority.setVisible(false);
            fieldTitle.setVisible(false);
            fieldDeadline.setVisible(false);
            comboEpicChoose.setVisible(false);
            buttonApprove.setVisible(false);
            if (Board.getEpicByName(comboEpicChoose.getValue()) != null){
                Epic epicToAdd = Board.getEpicByName(comboEpicChoose.getValue());
                Board.tasks.add(new Task(fieldTitle.getText(), 5, fieldPriority.getText(),
                        epicToAdd, User.currentUser, countCurrentTime(), fieldDeadline.getText()));
                System.out.println(Board.tasks.get(0).getUserName());
            }
            fieldComplexity.setText("");
            fieldPriority.setText("");
            fieldTitle.setText("");
            fieldDeadline.setText("");
        }
    }

    public void initialize(){
        labelEpicText.setVisible(false);
        fieldColorEpic.setVisible(false);
        fieldNameEpic.setVisible(false);
        buttonApproveEpic.setVisible(false);

        labelLind.setVisible(false);
        fieldComplexity.setVisible(false);
        fieldPriority.setVisible(false);
        fieldTitle.setVisible(false);
        fieldDeadline.setVisible(false);
        comboEpicChoose.setVisible(false);
        buttonApprove.setVisible(false);
        ObservableList<String> userList = FXCollections.observableArrayList(User.names);
        comboUsers.setItems(userList);
        ObservableList<String> epicList = FXCollections.observableArrayList(Epic.names);
        comboEpic.setItems(epicList);
        comboEpicChoose.setItems(epicList);
    }
}
