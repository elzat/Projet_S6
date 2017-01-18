package jeu.view;

import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    private ObjectProperty<Point2D> positionFollow;
    private SimpleIntegerProperty xFollow, yFollow, wFollow, hFollow;
    private Model model;
    private double widthLevel, heightLevel;

    public GameCanvas(double width, double height, Model model, double widthLevel, double heightLevel) {
        super(width, height);
        this.widthLevel = widthLevel;
        this.heightLevel = heightLevel;
        this.model = model;

        xFollow = new SimpleIntegerProperty(0);
        yFollow = new SimpleIntegerProperty(0);
        hFollow = new SimpleIntegerProperty(0);
        wFollow = new SimpleIntegerProperty(0);
        positionFollow = new SimpleObjectProperty<>(new Point2D(0, 0));

        wFollow.bind(widthProperty());
        hFollow.bind(heightProperty());

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
        if (positionFollow.isBound()) {

            double x = 0, y = 0;

            if (positionFollow.get().getX() > wFollow.get()) {
                x = positionFollow.get().getX() - wFollow.get();
            } else {
                if (positionFollow.get().getX() < xFollow.get()) {
                    x = positionFollow.get().getX() - xFollow.get();
                }
            }

            if (positionFollow.get().getY() > hFollow.get()) {
                y = positionFollow.get().getY() - hFollow.get();
            } else {
                if (positionFollow.get().getY() < yFollow.get()) {
                    y = positionFollow.get().getY() - yFollow.get();
                }
            }

            positionFenetre = positionFenetre.add(x, y);

            x = positionFenetre.getX();
            y = positionFenetre.getY();

            if (positionFenetre.getX() < 0) {
                x = 0;
            } else if (positionFenetre.getX() + widthLevel > model.getWidth()) {
                x = model.getWidth() - widthLevel;
            }

            if (positionFenetre.getY() < 0) {
                y = 0;
            } else if (positionFenetre.getY() + heightLevel > model.getHeight()) {
                y = model.getHeight() - heightLevel;
            }

            positionFenetre = new Point2D(x, y);

        }


        Collection<Collection<Pair<Point2D, Image>>> images = model.getImagesArea(new Rectangle2D(positionFenetre.getX(), positionFenetre.getY(), widthLevel, heightLevel));
        double ratioX = getWidth() / widthLevel;
        double ratioY = getHeight() / heightLevel;
        graphicsContext2D.clearRect(0, 0, getWidth(), getHeight());
        for (Collection<Pair<Point2D, Image>> image : images) {
            for (Pair<Point2D, Image> point2DImagePair : image) {
                Image img = point2DImagePair.getValue();
                Point2D pnt = point2DImagePair.getKey().subtract(positionFenetre);
                graphicsContext2D.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), pnt.getX() * ratioX, pnt.getY() * ratioY, img.getWidth() * ratioX, img.getHeight() * ratioY);
            }
        }

        graphicsContext2D.fillOval(positionFollow.get().getX(), positionFollow.get().getY(), 10, 10);


    }

    public void windowsFollow(Rectangle2D windows) {
        xFollow.bind(widthProperty().negate().multiply(windows.getMinX()));
        yFollow.bind(heightProperty().negate().multiply(windows.getMinY()));
        wFollow.bind(widthProperty().negate().multiply(windows.getMaxX()));
        hFollow.bind(heightProperty().negate().multiply(windows.getMaxY()));
    }

    public void centerFollow() {
        windowsFollow(new Rectangle2D(getWidth() / 2, getHeight() / 2, 0, 0));
    }

    public ObjectProperty<Point2D> positionFollow() {
        return positionFollow;
    }

}
