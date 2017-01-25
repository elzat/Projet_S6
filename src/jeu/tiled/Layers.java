package jeu.tiled;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * Created by crede on 24/01/2017.
 */
public class Layers {

    private ArrayList<Layer> layers;

    public Layers() {
        layers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Layers{" +
                "layers=" + layers +
                '}';
    }

    public void read(Node item) {
        layers.add(new Layer((Element) item));
    }
}
