package jeu.tiled;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by crede on 24/01/2017.
 */
public class TestTiled {

    public static void main(String[] args) {


        Map map = null;
        try {
            String name = TestTiled.class.getResource("../image/sans-titre.tmx").getFile();
            map = Map.makeMap(name);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        System.out.println(map);


    }
}
