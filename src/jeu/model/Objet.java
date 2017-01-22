package jeu.model;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Created by crede on 22/01/2017.
 */
public interface Objet {

    Point2D getPosition();

    Image getImage();

    Bounds getLimites();

}
