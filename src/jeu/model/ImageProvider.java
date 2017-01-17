package jeu.model;

import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by crede on 17/01/2017.
 */
public class ImageProvider {
    private static ImageProvider ourInstance = new ImageProvider();
    private HashMap<String, Image> imageMap;

    private ImageProvider() {
        imageMap = new HashMap<>();
    }

    public static Image get(URL file) throws IOException {
        String url = file.toString();
        Image image = ourInstance.imageMap.get(url);
        if (null == image) {
            image = new Image(url);
            ourInstance.imageMap.put(url, image);
        }
        return image;
    }

}
