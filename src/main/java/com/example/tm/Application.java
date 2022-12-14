package com.example.tm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Application extends javafx.application.Application {
    private static String username = "";
    public static Board board = new Board("Board");

    @Override
    public void start(Stage primaryStage) {
        board.addEpic("testEpic", "red");

        primaryStage.setTitle("Task Manager");
        primaryStage.setScene((EnterNameScreen.open()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Scene BoardScene() {
        board.addUser(Application.username);
        board.addTask("test", 2,"major", Application.username, "testEpic");
        board.addTask("aaaaaa", 2,"major", Application.username, "testEpic");
        board.addTask("aaaaaa", 2,"major", Application.username, "testEpic");
        board.addTask("aaaaaa", 2,"major", Application.username, "testEpic");
        board.addTask("aaaaaa", 2,"major", Application.username, "testEpic");
        board.addTask("aaaaaa", 2,"major", Application.username, "testEpic");
        ArrayList<GridPane> ta = new ArrayList<GridPane>();


        ObservableList<GridPane> langs = FXCollections.observableArrayList(ta);
        ListView<GridPane> langsListView = new ListView<GridPane>(langs);
        langsListView.minHeight(600);
        langsListView.minWidth(800);

        FlowPane root = new FlowPane(langsListView);


        return new Scene(root, 600,800);
    }

    public static void changeScene(Scene scene, ActionEvent event) {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
    }

    public static void setUsername(String username) {
        Application.username = username;
    }
}