package jeu.tiled;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by crede on 24/01/2017.
 */
public class Map {

    private String version;
    private Orientation orientation;
    private Renderorder renderorder;
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private Proprietes proprietes;
    private Layers layers;
    private TileSets tileSets;

    private Map(Element map) {
        version = map.getAttribute("version");
        orientation = Orientation.make(map.getAttribute("orientation"));
        renderorder = Renderorder.make(map.getAttribute("renderorder"));
        width = Integer.parseInt(map.getAttribute("width"));
        height = Integer.parseInt(map.getAttribute("height"));
        tileHeight = Integer.parseInt(map.getAttribute("tileheight"));
        tileWidth = Integer.parseInt(map.getAttribute("tilewidth"));
        proprietes = new Proprietes();
        layers = new Layers();
        tileSets = new TileSets();

        NodeList childNodes = map.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            switch (item.getNodeName()) {
                case "properties":
                    proprietes.read(item);
                    break;
                case "tileset":
                    tileSets.read((Element) item);
                    break;
                case "layer":
                    layers.read(item);
                    break;
            }
        }
    }

    public static Map makeMap(String fileName) throws ParserConfigurationException, IOException, SAXException {
        File tmxFile = new File(fileName);
        Document doc = null;
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(tmxFile);
        return new Map(doc.getDocumentElement());
    }

    @Override
    public String toString() {
        return "Map{" +
                "version='" + version + '\'' +
                ", orientation=" + orientation +
                ", renderorder=" + renderorder +
                ", width=" + width +
                ", height=" + height +
                ", tileWidth=" + tileWidth +
                ", tileHeight=" + tileHeight +
                ", proprietes=" + proprietes +
                ", layers=" + layers +
                ", tileSets=" + tileSets +
                '}';
    }

    public enum Orientation {
        Orthogonal;

        public static Orientation make(String name) {
            switch (name) {
                case "orthogonal":
                default:
                    return Orthogonal;
            }

        }
    }

    public enum Renderorder {
        Right_Down, Right_Up, Left_Down, Left_Up;

        public static Renderorder make(String name) {
            switch (name) {
                case "right-up":
                    return Right_Up;
                case "left-down":
                    return Left_Down;
                case "left-up":
                    return Left_Up;
                case "right-down":
                default:
                    return Right_Down;
            }
        }
    }

}
