package jeu.model.objets;

import javafx.scene.image.Image;

/**
 * Created by crede on 22/01/2017.
 */
public class Sol extends ObjetBasique {
    public Sol(double x, double y) {
        super(x, y);
    }

    @Override
    public Image getImage() {
        return TileSetBlock.Block.SOL.get().getTile();
    }
}
