import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Created by crede on 02/02/2017.
 */
public class RectangleInt2D {

    Rectangle2D rectangle2D;

    public RectangleInt2D(int x, int y, int w, int h) {
        rectangle2D = new Rectangle2D(x, y, w, h);
    }

    public int getMinX() {
        return (int) rectangle2D.getMinX();
    }

    public int getMinY() {
        return (int) rectangle2D.getMinY();
    }

    public int getWidth() {
        return (int) rectangle2D.getWidth();
    }

    public int getHeight() {
        return (int) rectangle2D.getHeight();
    }

    public int getMaxX() {
        return (int) rectangle2D.getMaxX();
    }

    public int getMaxY() {
        return (int) rectangle2D.getMaxY();
    }

    public boolean contains(PointInt2D p) {
        return rectangle2D.contains(p);
    }

    public boolean contains(double x, double y) {
        return rectangle2D.contains(x, y);
    }

    public boolean contains(Rectangle2D r) {
        return rectangle2D.contains(r);
    }

    public boolean contains(double x, double y, double w, double h) {
        return rectangle2D.contains(x, y, w, h);
    }

    public boolean intersects(Rectangle2D r) {
        return rectangle2D.intersects(r);
    }

    public boolean intersects(double x, double y, double w, double h) {
        return rectangle2D.intersects(x, y, w, h);
    }
}
