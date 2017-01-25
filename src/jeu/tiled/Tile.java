package jeu.tiled;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by crede on 24/01/2017.
 */
public class Tile {
    private int id;
    private Proprietes proprietes;
    private Animation animation;

    public Tile(Element item) {
        id = Integer.parseInt(item.getAttribute("id"));
        proprietes = new Proprietes();

        NodeList childNodes = item.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item1 = childNodes.item(i);
            switch (item1.getNodeName()) {
                case "properties":
                    proprietes.read(item1);
                    break;
                case "animation":
                    animation = new jeu.tiled.Animation(item1);
                    break;
            }

        }

    }

    @Override
    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", proprietes=" + proprietes +
                ", animation=" + animation +
                '}';
    }
}
