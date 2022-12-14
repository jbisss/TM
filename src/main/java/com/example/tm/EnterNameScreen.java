package com.example.tm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EnterNameScreen {
    public static Scene open() {
        AnchorPane grid = new AnchorPane();

        Label label1 = new Label("Name:");
        label1.setLayoutX(100);
        grid.getChildren().add(label1);

        //Defining the Name text field
        final TextField name = new TextField();
        name.setLayoutX(50);
        name.setLayoutY(30);
        name.setPromptText("Enter your first name.");
        grid.getChildren().add(name);

        //Defining the Submit button
        Button submit = new Button("Submit");
        submit.setLayoutX(92);
        submit.setLayoutY(60);
        grid.getChildren().add(submit);

        //Adding a Label
        final Label label = new Label();
        label.setLayoutX(60);
        label.setLayoutY(90);
        grid.getChildren().add(label);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((name.getText() != null && !name.getText().isEmpty())) {
                    Application.setUsername(name.getText());
                    User newUser = new User(name.getText());
                    Board.users.add(newUser);
                    User.currentUser = newUser;
                    Application.changeScene(TestScene.open(), e);
                } else {
                    label.setText("Enter your name first.");
                }
            }
        });

        return new Scene(grid, 250, 120);
    }
}
