package jeu.tiled;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by crede on 25/01/2017.
 */
public class Layer {
    private String name;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Proprietes proprietes;
    private Data data;

    public Layer(Element item) {
        name = item.getAttribute("name");
        x = item.hasAttribute("x") ? Integer.parseInt(item.getAttribute("x")) : 0;
        y = item.hasAttribute("y") ? Integer.parseInt(item.getAttribute("y")) : 0;

        width = Integer.parseInt(item.getAttribute("width"));
        height = Integer.parseInt(item.getAttribute("height"));

        visible = item.hasAttribute("visible") ? Boolean.parseBoolean(item.getAttribute("visible")) : true;

        proprietes = new Proprietes();
        NodeList childNodes = item.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item1 = childNodes.item(i);
            switch (item1.getNodeName()) {
                case "properties":
                    proprietes.read(item1);
                    break;
                case "data":
                    data = new Data(item1);
                    break;
            }
        }

    }

    @Override
    public String toString() {
        return "Layer{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", visible=" + visible +
                ", proprietes=" + proprietes +
                ", data=" + data +
                '}';
    }
}
