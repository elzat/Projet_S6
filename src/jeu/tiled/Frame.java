package jeu.tiled;

import org.w3c.dom.Element;

/**
 * Created by crede on 25/01/2017.
 */
public class Frame {
    private int tileId;
    private int duration;

    public Frame(Element item1) {
        tileId = Integer.parseInt(item1.getAttribute("tileid"));
        duration = Integer.parseInt(item1.getAttribute("duration"));
    }

    @Override
    public String toString() {
        return "Frame{" +
                "tileId=" + tileId +
                ", duration=" + duration +
                '}';
    }
}
