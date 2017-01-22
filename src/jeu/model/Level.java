package jeu.model;

import jeu.model.objets.*;

/**
 * Created by crede on 17/01/2017.
 */
public class Level {
    private Objet objets[][];

    public Level(int[][] tileMap) {
        int h = TileSetBlock.getInstance().getTileHeight();
        int w = TileSetBlock.getInstance().getTileWidth();


        this.objets = new Objet[tileMap.length][];
        for (int i = 0; i < tileMap.length; i++) {
            objets[i] = new Objet[tileMap[i].length];
            for (int j = 0; j < tileMap[i].length; j++) {
                Objet obj = null;
                switch (tileMap[i][j]) {
                    case 0:
                        obj = new Vide(i * w, j * h);
                        break;
                    case 1:
                        obj = new PointInterogation(i * w, j * h);
                        break;
                    case 2:
                        obj = new Brique(i * w, j * h);
                        break;

                    case 3:
                        obj = new Tuyaux_Top_Gauche(i * w, j * h);
                        break;

                    case 4:
                        obj = new Tuyaux_Droite(i * w, j * h);
                        break;
                    case 5:
                        obj = new Tuyaux_Gauche(i * w, j * h);
                        break;
                    case 6:
                        obj = new Tuyaux_Droite(i * w, j * h);
                        break;
                    case 7:
                        obj = new Sol(i * w, j * h);
                        break;
                }
                objets[i][j] = obj;
            }
        }
    }

    public int getTileHeight() {
        return TileSetBlock.getInstance().getTileHeight();
    }

    public int getTileWidth() {
        return TileSetBlock.getInstance().getTileWidth();
    }

    public Objet getObjet(int x, int y) {
        return objets[y][x];
    }

    public int getWidth() {
        return objets[0].length * getTileWidth();
    }

    public int getHeight() {
        return objets.length * getTileHeight();
    }
}
