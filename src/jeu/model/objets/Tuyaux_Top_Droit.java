package jeu.model.objets;

import javafx.scene.image.Image;

/**
 * Created by crede on 22/01/2017.
 */
public class Tuyaux_Top_Droit extends ObjetBasique {

    public Tuyaux_Top_Droit(double x, double y) {
        super(x, y);
    }


    @Override
    public Image getImage() {
        return TileSetBlock.Block.TUYAUX_TOP_DROIT.get().getTile();
    }

}
