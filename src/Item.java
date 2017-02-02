/**
 * Created by crede on 31/01/2017.
 */
public interface Item {
    PointInt2D getPosition();

    boolean isPassable();

    RectangleInt2D getBounds();
}
