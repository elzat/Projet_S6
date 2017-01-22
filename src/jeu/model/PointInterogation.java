package jeu.model;

import javafx.scene.image.Image;
import jeu.model.objets.ObjetBasique;
import jeu.model.objets.TileSetBlock;

/**
 * Created by crede on 22/01/2017.
 */
public class PointInterogation extends ObjetBasique {
    protected PointInterogation(double x, double y) {
        super(x, y);
    }

    @Override
    public Image getImage() {
        return TileSetBlock.Block.POINT_INTEROGATION.get().getTile();
    }
}
