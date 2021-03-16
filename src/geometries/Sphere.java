package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Sphere class represents sphere in 3D Cartesian coordinate
 * system
 *
 * @author Achinoam and Yael
 */
public class Sphere implements Geometry {
    /**
     * The center point of the sphere
     */
     Point3D _center;
    /**
     * The radius of the sphere
     */
    final double radius;

    /**
     * Sphere constractor
     *
     * @param center
     * @param radius
     */
    public Sphere(Point3D center, double radius) {
        _center = center;
        this.radius = radius;
    }

    /**
     *
     * @param point for calculate the normal vector
     * @return the normal vector of the sphere
     */

    @Override
    public Vector getNormal(Point3D point) {

        if(point.equals(_center)){
            throw new IllegalArgumentException("CANNOT CREATE VECTOR 0");
        }
        //Vector n=new Vector(point.subtract(_center).getHead());
        Vector n=point.subtract(_center);
        n.normalize();
        return n;
    }

    public Point3D getCenter() {
        return _center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        return null;
    }
}
