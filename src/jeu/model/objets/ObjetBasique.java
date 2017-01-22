package jeu.model.objets;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import jeu.model.Objet;

/**
 * Created by crede on 22/01/2017.
 */
public abstract class ObjetBasique implements Objet {

    private Point2D position;

    protected ObjetBasique(double x, double y) {
        this.position = new Point2D(x, y);
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public Bounds getLimites() {
        Image img = getImage();
        return new BoundingBox(position.getX(), position.getY(), img.getWidth(), img.getHeight());
    }
}
