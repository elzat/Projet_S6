package jeu;

import javafx.geometry.Point2D;

/**
 * Created by crede on 28/01/2017.
 */
public interface Objet_Mobile extends Objet {
    Point2D getVitesse();

    void setVitesse(Point2D v);

    void setVitesse(double vx, double vy);

    void setPosition(Point2D p);

    void setPosition(double x, double y);

}
