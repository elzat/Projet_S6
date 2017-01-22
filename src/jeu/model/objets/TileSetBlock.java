package jeu.model.objets;

import jeu.model.Tile;
import jeu.model.TileSet;

import java.io.IOException;

/**
 * Created by crede on 21/01/2017.
 */
public class TileSetBlock {
    private static TileSetBlock ourInstance = new TileSetBlock();
    private TileSet tileSet;

    private TileSetBlock() {
        try {
            tileSet = new TileSet(this.getClass().getResource("/jeu/image/tileset.png"), 8, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TileSet getInstance() {
        return ourInstance.tileSet;
    }

    public enum Block {
        VIDE(0), POINT_INTEROGATION(1), BRIQUE(2), TUYAUX_TOP_GAUCHE(3), TUYAUX_TOP_DROIT(4), TUYAUX_GAUCHE(5), TUYAUX_DROIT(6), SOL(7);

        private int number;

        private Block(int number) {
            this.number = number;
        }

        public Tile get() {
            return getInstance().getTile(number);
        }

    }


}
