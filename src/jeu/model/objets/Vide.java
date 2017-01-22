package jeu.model.objets;

import javafx.scene.image.Image;

/**
 * Created by crede on 22/01/2017.
 */
public class Vide extends ObjetBasique {
    public Vide(double x, double y) {
        super(x, y);
    }

    @Override
    public Image getImage() {
        return TileSetBlock.Block.VIDE.get().getTile();
    }
}
