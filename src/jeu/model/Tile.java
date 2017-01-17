package jeu.model;

import javafx.scene.image.Image;

/**
 * Created by crede on 17/01/2017.
 */
public class Tile {
    TileSet tileSet;
    int tileNumber;

    public Tile(TileSet tileSet, int tileNumber) {
        this.tileSet = tileSet;
        this.tileNumber = tileNumber;
    }

    public int getTileHeight() {
        return tileSet.getTileHeight();
    }

    public int getTileWidth() {
        return tileSet.getTileWidth();
    }

    public Image getTile() {
        return tileSet.getImageTile(tileNumber);
    }
}
