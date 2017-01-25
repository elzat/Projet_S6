package jeu.tiled;

import org.w3c.dom.Element;

/**
 * Created by crede on 25/01/2017.
 */
public class Image {

    private javafx.scene.image.Image image;

    public Image(Element item1) {
        String name = item1.getAttribute("source");
        image = new javafx.scene.image.Image(getClass().getResourceAsStream("/jeu/image/" + name));
    }

    @Override
    public String toString() {
        return "Image{" +
                "image=" + image +
                '}';
    }
}
