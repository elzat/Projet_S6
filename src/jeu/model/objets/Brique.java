package jeu.model.objets;

import javafx.scene.image.Image;

/**
 * Created by crede on 22/01/2017.
 */
public class Brique extends ObjetBasique {

    public Brique(double x, double y) {
        super(x, y);
    }


    @Override
    public Image getImage() {
        return TileSetBlock.Block.BRIQUE.get().getTile();
    }

}
