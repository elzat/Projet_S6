package jeu.tiled;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by crede on 24/01/2017.
 */
public class Proprietes {

    private HashMap<String, Propriete> proprieteHashMap = new HashMap<>();


    public <T> void put(String key, T value) {
        proprieteHashMap.put(key, new Propriete<T>(key, value));
    }

    public Set<String> keySet() {
        return proprieteHashMap.keySet();
    }

    public boolean containsKey(String key) {
        return proprieteHashMap.containsKey(key);
    }

    public <T> T getOrDefault(String key, T defaultValue) {
        Propriete p = proprieteHashMap.get(key);
        if (null == p) {
            return defaultValue;
        }
        try {
            return (T) p.get();
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    @Override
    public String toString() {
        return "Proprietes{" +
                "proprieteHashMap=" + proprieteHashMap +
                '}';
    }

    public void read(Node item) {
        NodeList childNodes = item.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item1 = childNodes.item(i);
            switch (item1.getNodeName()) {
                case "property":
                    Propriete read = Propriete.read((Element) item1);
                    proprieteHashMap.put(read.name(), read);
                    break;
            }

        }

    }
}
