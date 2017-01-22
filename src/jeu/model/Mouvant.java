package jeu.model;

import javafx.geometry.Point2D;

/**
 * Created by crede on 22/01/2017.
 */
public interface Mouvant extends Objet {

    void setPosition(Point2D position);

    Point2D getVitesse();

    void setVitesse(Point2D vitesse);

}
