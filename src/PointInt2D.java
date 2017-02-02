import javafx.geometry.Point2D;

/**
 * Created by crede on 02/02/2017.
 */
public class PointInt2D extends Point2D {


    public PointInt2D(int x, int y) {
        super(x, y);
    }

    public PointInt2D add(int x, int y) {
        return new PointInt2D((int) getX() + x, (int) getY() + y);
    }

    public PointInt2D add(PointInt2D point) {
        return add((int) point.getX(), (int) point.getY());
    }

    public PointInt2D subtract(int x, int y) {
        return add(-x, -y);
    }

    public PointInt2D multiply(int factor) {
        return new PointInt2D((int) getX() * factor, (int) getY() * factor);
    }

    public PointInt2D subtract(PointInt2D point) {
        return subtract((int) point.getX(), (int) point.getY());
    }

    public PointInt2D normalize() {
        int a = (int) Math.abs(getX());
        int b = (int) Math.abs(getY());
        if (a == 0 || b == 0) {
            return this;
        }

        if (a < b) {
            int c = a;
            a = b;
            b = c;
        }
        int r;
        do {
            r = a % b;
            a = b;
            b = r;
        } while (r != 0);

        return new PointInt2D((int) getX() / a, (int) getY() / a);
    }
}
