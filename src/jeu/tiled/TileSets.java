package jeu.tiled;

import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by crede on 24/01/2017.
 */
public class TileSets {

    ArrayList<TileSet> tileSets;

    public TileSets() {
        tileSets = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "TileSets{" +
                "tileSets=" + tileSets +
                '}';
    }

    public void read(Element item) {
        tileSets.add(new TileSet(item));
    }
}
