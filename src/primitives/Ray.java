package primitives;

import java.util.Objects;

/**
 * @author Achinoam and Yael
 * /**
 *  * "Half-straight - all the points on the line that are on one side of the point given on the line called the beginning / beginning / beginning of the fund):
 */
public class Ray {
    Point3D _p0;
    Vector _dir;

    /**
     * constructor
     *
     * @param p0  point
     * @param dir vector
     */
    public Ray(Point3D p0, Vector dir) {
        this._p0 = p0;
        this._dir = dir;
        this._dir = this._dir.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    @Override
    public String toString() {
        return "" + _p0.toString() + ":" + _dir.toString();
    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }
}
