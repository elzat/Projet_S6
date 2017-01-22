package jeu.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by crede on 17/01/2017.
 */
public class Model {

    private Level level;

    
    public Model() {
        level = new Level(new int[][]
                        {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 3, 4, 0, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2},
                                {0, 0, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}});
    }

    public Collection<Collection<Pair<Point2D, Image>>> getImagesArea(Rectangle2D rectangle2D) {
        Collection<Collection<Pair<Point2D, Image>>> retour = new LinkedList<>();
        retour.add(getImagesTilesArea(rectangle2D));
        return retour;
    }

    private Collection<Pair<Point2D, Image>> getImagesTilesArea(Rectangle2D rectangle2D) {
        Collection<Pair<Point2D, Image>> retour = new LinkedList<>();
        double w = Math.min(rectangle2D.getWidth(), level.getWidth());
        double h = Math.min(rectangle2D.getHeight(), level.getHeight());
        double y = Math.max(rectangle2D.getMinY(), 0);
        double x = Math.max(rectangle2D.getMinX(), 0);
        x = Math.min(x, level.getWidth() - w);
        y = Math.min(y, level.getHeight() - h);


        rectangle2D = new Rectangle2D(x, y, w, h);
        for (int i = (int) rectangle2D.getMinX() / level.getTileWidth(); i < rectangle2D.getMaxX() / level.getTileWidth(); i++) {
            for (int j = (int) rectangle2D.getMinY() / level.getTileHeight(); j < rectangle2D.getMaxY() / level.getTileHeight(); j++) {
                retour.add(new Pair<>(new Point2D(i * level.getTileWidth(), j * level.getTileHeight()), level.getObjet(i, j).getImage()));
            }
        }
        return retour;
    }

    public int getWidth() {
        return level.getWidth();
    }

    public int getHeight() {
        return level.getHeight();
    }
}
