package jeu.tiled;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.HashMap;

/**
 * Created by crede on 24/01/2017.
 */
public class Tiles {

    HashMap<Integer, Tile> tiles;

    public Tiles() {
        tiles = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Tiles{" +
                "tiles=" + tiles +
                '}';
    }

    public void read(Node item1) {
        Tile t = new Tile((Element) item1);
    }
}
