package jeu.controller;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jeu.model.Model;
import jeu.view.GameCanvas;

/**
 * Created by crede on 17/01/2017.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        GameCanvas game = new GameCanvas(16 * 5, 16 * 5, model, 16 * 5, 16 * 5);
        primaryStage.setScene(new Scene(new Group(game), game.getWidth(), game.getHeight()));

        game.heightProperty().bind(primaryStage.getScene().heightProperty());
        game.widthProperty().bind(primaryStage.getScene().widthProperty());

        SimpleObjectProperty<Point2D> point = new SimpleObjectProperty<>(new Point2D(0, 0));
        game.follow(point);


        game.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.D)
                    point.set(point.getValue().add(1, 0));
                if (event.getCode() == KeyCode.Q)
                    point.set(point.getValue().add(-1, 0));
                if (event.getCode() == KeyCode.Z)
                    point.set(point.getValue().add(0, -1));
                if (event.getCode() == KeyCode.S)
                    point.set(point.getValue().add(0, 1));
            }
        });
        game.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.Q)
                    point.set(point.getValue().add(1, 0));
                if (event.getCode() == KeyCode.D)
                    point.set(point.getValue().add(-1, 0));
                if (event.getCode() == KeyCode.S)
                    point.set(point.getValue().add(0, -1));
                if (event.getCode() == KeyCode.Z)
                    point.set(point.getValue().add(0, 1));
            }
        });

        game.requestFocus();
        primaryStage.show();
    }
}
