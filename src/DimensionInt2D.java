import javafx.geometry.Dimension2D;

/**
 * Created by crede on 02/02/2017.
 */
public class DimensionInt2D {

    Dimension2D dimension2D;

    public DimensionInt2D(int w, int h) {
        dimension2D = new Dimension2D(w, h);
    }

    public int getWidth() {
        return (int) dimension2D.getWidth();
    }

    public int getHeight() {
        return (int) dimension2D.getHeight();
    }

    @Override
    public boolean equals(Object obj) {
        return dimension2D.equals(obj);
    }
}
