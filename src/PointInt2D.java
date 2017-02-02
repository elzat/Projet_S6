import javafx.beans.NamedArg;
import javafx.geometry.Point2D;

import java.math.BigInteger;

/**
 * Created by crede on 02/02/2017.
 */
public class PointInt2D {

    Point2D point2D;

    public PointInt2D(int x, int y) {
        point2D = new Point2D(x, y);
    }

    public int getX() {
        return (int) point2D.getX();
    }

    public int getY() {
        return (int) point2D.getY();
    }

    public PointInt2D add(int x, int y) {
        return new PointInt2D(getX() + x, getY() + y);
    }

    public PointInt2D add(PointInt2D point) {
        return add(point.getX(), point.getY());
    }

    public PointInt2D subtract(int x, int y) {
        return add(-x, -y);
    }

    public PointInt2D multiply(int factor) {
        return new PointInt2D(getX() * factor, getY() * factor);
    }

    public PointInt2D subtract(PointInt2D point) {
        return subtract(point.getX(), point.getY());
    }

    public PointInt2D normalize() {
        int a = Math.abs(getX());
        int b = Math.abs(getY());
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

        return new PointInt2D(getX() / a, getY() / a);
    }

    @Override
    public boolean equals(Object obj) {
        return point2D.equals(obj);
    }
}
