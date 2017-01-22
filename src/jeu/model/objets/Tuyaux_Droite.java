package jeu.model.objets;

import javafx.scene.image.Image;

/**
 * Created by crede on 22/01/2017.
 */
public class Tuyaux_Droite extends ObjetBasique {

    public Tuyaux_Droite(double x, double y) {
        super(x, y);
    }


    @Override
    public Image getImage() {
        return TileSetBlock.Block.TUYAUX_DROIT.get().getTile();
    }

}
