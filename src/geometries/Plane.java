package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Plane class represent A point in space and  a vertical vector
 * system
 *
 * @author Achinoam and Yael
 */
public class Plane implements Geometry {
    /**
     * Point and vector
     */
    final Point3D _q0;
    final Vector _normal;

    /**
     * Seconed constractor, gets vector and point
     *
     * @param q0     point
     * @param normal vector
     */
    public Plane(Point3D q0, Vector normal) {
        _q0 = q0;
        _normal = normal.normalized();
    }

    /**
     * Plane constractor, gets 3 points
     *
     * @param p1
     * @param p2
     * @param p3
     * the constructor also calculate the normal of the plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);

        N.normalize();

        _normal = N;
    }

    public Vector getNormal() {

        return _normal;
    }



    public Point3D getQ0() {
        return _q0;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        return null;
    }
}
