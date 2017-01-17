package jeu.controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
        GameCanvas game = new GameCanvas(200, 500, model, model.getWidth(), model.getHeight());
        primaryStage.setScene(new Scene(new Group(game)));

        game.heightProperty().bind(primaryStage.getScene().heightProperty());
        game.widthProperty().bind(primaryStage.getScene().widthProperty());
        primaryStage.show();
    }
}
