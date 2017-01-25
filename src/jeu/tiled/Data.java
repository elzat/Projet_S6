package jeu.tiled;

import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * Created by crede on 25/01/2017.
 */
public class Data {

    private ArrayList<Integer> data;

    public Data(Node item1) {
        data = new ArrayList<>();
        String[] value = item1.getTextContent().replaceAll("\\s", "").split(",");
        for (String s : value
                ) {
            data.add(Integer.parseInt(s));
        }


    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }

}
