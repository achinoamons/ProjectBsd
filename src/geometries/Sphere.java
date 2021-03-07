package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere implements Geometry{
   final Point3D _center;
    final double radius;

    public Sphere(Point3D center, double radius) {
        _center = center;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
