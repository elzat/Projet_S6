import java.awt.geom.Point2D;

/**
 * Created by crede on 31/01/2017.
 */
public interface MobileItem extends Item {

    Point2D getSpeed();

    void setSpeed(Point2D speed);

    void setSpeed(double vx, double vy);

    void setPosition(Point2D position);

    void setPosition(double x, double y);

}
