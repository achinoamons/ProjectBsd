package geometries;

import primitives.Point3D;
import primitives.Vector;

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
    final Point3D _center;
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

    @Override
    public Vector getNormal(Point3D point) {
        return null;
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
}
