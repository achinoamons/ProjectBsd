package geometries;

import primitives.*;

/**
 * Tube class represent pipe in 3D Cartesian coordinate system
 *
 * @author Achinoam and Yael
 */
public class Tube implements Geometry {
    /**
     * Ray
     */
    final Ray _axisRay;
    /**
     * radius
     */
    final double _radius;

    /**
     * constructor
     *
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    public Ray getAxisRay() {
        return _axisRay;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }
}
