package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

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

    /**
     *
     * @param point for calculate the normal
     * @return the normal of the tube
     */
    @Override
    public Vector getNormal(Point3D point) {
//        //get o:
//        Point3D o=_axisRay.getP0();
//        Vector v=_axisRay.getDir();
//        Vector v1=point.subtract(o);
//        //get the levy t
//        double t=v1.dotProduct(v);
//        if(isZero(t))
//        {   return v1;
//
//        }
//        o=o.add(v.scale(t));
//        //get the normal:
//        Vector n=point.subtract(o);
//        return  n.normalize();
        Point3D p0=_axisRay.getP0();
        Vector v=_axisRay.getDir();
        Vector p0_point=point.subtract(p0);
        double t=v.dotProduct(p0_point);
        if(isZero(t)){
            //הוספתי פה נרמול
            return p0_point.normalize();

        }
        Point3D o=p0.add(v.scale(t));
        Vector n=point.subtract(o);
        return  n.normalize();


    }
//    @Override
//    public Vector getNormal(Point3D p) {
//        Vector v1 = new Vector(_axisRay.getDir());
//        Vector v2 = new Vector(p.subtract(_axisRay.getP0()));
//        double t = v1.dotProduct(v2);
//        Point3D nb = new Point3D(_axisRay._POO.add(_axisRay._direction.scale(t)));
//        Vector n = new Vector(nb.subtract(Point3D.ZERO));
//        return n;
//    }


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

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        return null;
    }
}
