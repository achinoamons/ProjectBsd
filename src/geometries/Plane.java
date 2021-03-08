package geometries;

import primitives.Point3D;
import primitives.Vector;

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
     * @param vertex
     * @param vertex1
     * @param vertex2
     */
    public Plane(Point3D vertex, Point3D vertex1, Point3D vertex2) {
        _q0 = vertex;
        _normal = null;
    }

    public Vector getNormal() {
        //to do
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
        return null;
    }

}
