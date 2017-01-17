package jeu.view;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import jeu.model.Model;

import java.util.Collection;

/**
 * Created by crede on 17/01/2017.
 */
public class GameCanvas extends Canvas {
    private Point2D positionFenetre;
    private Model model;
    private double widthLevel, heightLevel;

    public GameCanvas(double width, double height, Model model, double widthLevel, double heightLevel) {
        super(width, height);
        this.widthLevel = widthLevel;
        this.heightLevel = heightLevel;
        this.model = model;
        this.positionFenetre = new Point2D(0, 0);
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw();
            }
        };
        at.start();

    }

    public void draw() {
        GraphicsContext graphicsContext2D = getGraphicsContext2D();
        Collection<Collection<Pair<Point2D, Image>>> images = model.getImagesArea(new Rectangle2D(positionFenetre.getX(), positionFenetre.getY(), widthLevel, heightLevel));
        double ratioX = getWidth() / widthLevel;
        double ratioY = getHeight() / heightLevel;
        graphicsContext2D.clearRect(0, 0, getWidth(), getHeight());
        for (Collection<Pair<Point2D, Image>> image : images) {
            for (Pair<Point2D, Image> point2DImagePair : image) {
                Image img = point2DImagePair.getValue();
                Point2D pnt = point2DImagePair.getKey().subtract(positionFenetre);
                //graphicsContext2D.drawImage(img,pnt.getX(), pnt.getY());
                graphicsContext2D.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), pnt.getX() * ratioX, pnt.getY() * ratioY, img.getWidth() * ratioX, img.getHeight() * ratioY);
            }
        }
    }


}