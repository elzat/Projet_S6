package jeu.tiled;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by crede on 24/01/2017.
 */
public class TileSet {
    private int firstGID;
    private String name;
    private int tileWidth;
    private int tileHeight;
    private int spacing;
    private int margin;
    private int tileCount;
    private int columns;
    private Proprietes proprietes;
    private Image image;
    private Tiles tiles;

    public TileSet(Element item) {
        firstGID = Integer.parseInt(item.getAttribute("firstgid"));
        name = item.getAttribute("name");
        tileWidth = Integer.parseInt(item.getAttribute("tilewidth"));
        tileHeight = Integer.parseInt(item.getAttribute("tileheight"));
        spacing = item.hasAttribute("spacing") ? Integer.parseInt(item.getAttribute("spacing")) : 0;
        margin = item.hasAttribute("margin") ? Integer.parseInt(item.getAttribute("margin")) : 0;
        tileCount = Integer.parseInt(item.getAttribute("tilecount"));
        columns = Integer.parseInt(item.getAttribute("columns"));

        proprietes = new Proprietes();
        tiles = new Tiles();

        NodeList childNodes = item.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item1 = childNodes.item(i);
            switch (item1.getNodeName()) {
                case "properties":
                    proprietes.read(item1);
                    break;
                case "tile":
                    tiles.read(item1);
                    break;
                case "image":
                    image = new Image((Element) item1);
                    break;
            }

        }
    }

    @Override
    public String toString() {
        return "TileSet{" +
                "firstGID=" + firstGID +
                ", name='" + name + '\'' +
                ", tileWidth=" + tileWidth +
                ", tileHeight=" + tileHeight +
                ", spacing=" + spacing +
                ", margin=" + margin +
                ", tileCount=" + tileCount +
                ", columns=" + columns +
                ", proprietes=" + proprietes +
                ", image=" + image +
                ", tiles=" + tiles +
                '}';
    }

}
