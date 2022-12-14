package com.example.tm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestScene {
    public static Scene open() {
        Button btn = new Button();
        btn.setText("open login");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Application.changeScene(EnterNameScreen.open(), event);
            }
        });

        Button btn1 = new Button();
        btn1.setText("open tasks");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Application.changeScene(Application.BoardScene(), event);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Register");
                    stage.setScene(scene);
                    stage.show();
                    Stage stagePrev = (Stage) btn1.getScene().getWindow();
                    stagePrev.hide();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        AnchorPane root = new AnchorPane();
        HBox hBox = new HBox();
        Text text = new Text();

        hBox.getChildren().addAll(btn, btn1);
        root.getChildren().addAll(hBox, text);
        Scene scene = new Scene(root, 145, 25);
        text.setLayoutX(60);
        text.setLayoutY(150);
        return scene;
    }
}
