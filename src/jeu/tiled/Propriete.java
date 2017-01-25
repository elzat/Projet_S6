package jeu.tiled;

import javafx.scene.paint.Color;
import org.w3c.dom.Element;

import java.io.File;

/**
 * Created by crede on 24/01/2017.
 */
public class Propriete<T> {
    private T value;
    private String name;

    public Propriete(String name, T v) {
        this.name = name;
        value = v;
    }

    public static Propriete read(Element item1) {
        String name = item1.getAttribute("name");
        Class type;
        String value = item1.getAttribute("value");
        switch (item1.getAttribute("type")) {
            case "float":
                return new Propriete<Float>(name, Float.parseFloat(value));
            case "bool":
                return new Propriete<Boolean>(name, Boolean.parseBoolean(value));
            case "color":
                value = "0x" + value.substring(3) + value.substring(1, 3);
                return new Propriete<Color>(name, Color.web(value));
            case "file":
                return new Propriete<File>(name, new File(value));
            case "int":
                return new Propriete<Integer>(name, Integer.parseInt(value));
            case "string":
            default:
                return new Propriete<String>(name, value);
        }
    }

    public String name() {
        return name;
    }

    public T get() {
        return value;
    }

    @Override
    public String toString() {
        return "Propriete{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

}
