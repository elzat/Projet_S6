package jeu.tiled;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by crede on 25/01/2017.
 */
public class Animation {
    private ArrayList<Frame> frames;

    public Animation(Node item) {
        frames = new ArrayList<>();
        NodeList childNodes = item.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item1 = childNodes.item(i);
            switch (item1.getNodeName()) {
                case "frame":
                    frames.add(new Frame((Element) item1));
                    break;
            }

        }
    }

    @Override
    public String toString() {
        return "Animation{" +
                "frames=" + frames +
                '}';
    }
}
