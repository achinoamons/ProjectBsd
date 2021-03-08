package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class represents cylinder in 3D Cartesian coordinate
 * system
 *
 * @author Achinoam  and Yael
 */

public class Cylinder extends Tube {
    double _height;

    /**
     * @param axisRay
     * @param radius
     * @param height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        _height = height;
    }

    public Vector getNormal(Point3D p) {
        //to do
        return null;
    }

    //getter
    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                ", _axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }
}
