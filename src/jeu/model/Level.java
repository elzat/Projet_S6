package jeu.model;

import java.io.IOException;
import java.net.URL;

/**
 * Created by crede on 17/01/2017.
 */
public class Level {
    private TileSet tileSet;
    private Tile tiles[][];

    public Level(URL tileSetName, int nbX, int nbY, int[][] tileMap) {
        try {
            this.tileSet = new TileSet(ImageProvider.get(tileSetName), nbX, nbY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.tiles = new Tile[tileMap.length][];
        for (int i = 0; i < tileMap.length; i++) {
            tiles[i] = new Tile[tileMap[i].length];
            for (int j = 0; j < tileMap[i].length; j++) {
                tiles[i][j] = tileSet.getTile(tileMap[i][j]);
            }
        }
    }

    public int getTileHeight() {
        return tileSet.getTileHeight();
    }

    public int getTileWidth() {
        return tileSet.getTileWidth();
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public int getWidth() {
        return tiles[0].length * getTileWidth();
    }

    public int getHeight() {
        return tiles.length * getTileHeight();
    }
}
