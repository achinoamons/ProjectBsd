package geometries;
import  primitives.*;
public class Tube {
   final Ray _axisRay;
   final double _radius;

    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }
    public Vector getNormal(Point3D p) {
        //to do
        return null;
    }
}
