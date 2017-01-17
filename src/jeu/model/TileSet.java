package jeu.model;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by crede on 17/01/2017.
 */
public class TileSet {

    private Image setImage;
    private int nbX, nbY;
    private int tileHeight, tileWidth;

    public TileSet(URL imageName, int nbX, int nbY) throws IOException {
        this(ImageProvider.get(imageName), nbX, nbY);
    }

    public TileSet(Image setImage, int nbX, int nbY) {
        if (!(nbX >= 1 && nbY >= 1)) throw new AssertionError();
        this.setImage = setImage;
        this.nbX = nbX;
        this.nbY = nbY;
        tileHeight = (int) setImage.getHeight() / nbY;
        tileWidth = (int) setImage.getWidth() / nbX;
    }

    public int getNbX() {
        return nbX;
    }

    public int getNbY() {
        return nbY;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public Tile getTile(int x, int y) {
        if (!(x >= 0 && x < nbX && y >= 0 && y < nbY)) throw new AssertionError();
        return getTile((y + 1) * nbX);
    }

    public Tile getTile(int tileNumber) {
        if (!(tileNumber >= 0 && tileNumber < nbX * nbY)) throw new AssertionError();
        return new Tile(this, tileNumber);
    }

    public Image getImageTile(int x, int y) {
        if (!(x >= 0 && x < nbX && y >= 0 && y < nbY)) throw new AssertionError();
        return new WritableImage(setImage.getPixelReader(), x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }

    public Image getImageTile(int tileNumber) {
        if (!(tileNumber >= 0 && tileNumber < nbX * nbY)) throw new AssertionError();
        return getImageTile(tileNumber % nbX, tileNumber / nbX);
    }
}
