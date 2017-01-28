package jeu;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

/**
 * Created by crede on 28/01/2017.
 */
public interface Objet {
    Point2D getPosition();

    Bounds getLimite();

    boolean isFranchissable();

}
