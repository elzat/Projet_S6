/**
 * Created by crede on 31/01/2017.
 */
public interface MobileItem extends Item {

    PointInt2D getSpeed();

    void setSpeed(PointInt2D speed);

    void setSpeed(int vx, int vy);

    void setPosition(PointInt2D position);

    void setPosition(int x, int y);

}
