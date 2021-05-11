package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Sphere class represents sphere in 3D Cartesian coordinate
 * system
 *
 * @author Achinoam and Yael
 */
public class Sphere extends Geometry {
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
     * @param point for calculate the normal vector
     * @return the normal vector of the sphere
     */

    @Override
    public Vector getNormal(Point3D point) {

        if (point.equals(_center)) {
            throw new IllegalArgumentException("CANNOT CREATE VECTOR 0");
        }
        //Vector n=new Vector(point.subtract(_center).getHead());
        Vector n = point.subtract(_center);
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

    //calculating the point/s of intersection


    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        //calculating varieables for the final formula
        Point3D P0 = ray.getP0();
        Vector V = ray.getDir();
        Point3D O = _center;
        if(P0.equals(_center)){
            return  List.of(new GeoPoint(this,_center.add(V.scale(radius))));
        }
        Vector U = O.subtract(P0);
        double tm = V.dotProduct(U);
        double d =alignZero(Math.sqrt(alignZero(U.lengthSquared() - tm * tm)));
        //there is no intersaction
        //
        if(d>=radius){
            return null;}
        double th =alignZero(Math.sqrt(alignZero(radius*radius-d*d)));
        double t1=alignZero(tm-th);
        double t2=alignZero(tm+th);
        // if P is on the surface---
        if(isZero(th)){
            return null;
        }

        //in case of 2 intersaction points
        if(t1>0&&t2>0)
        {
//            //the first point and the second point

            return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint (this,ray.getPoint(t2)));
        }
        //in case of 1 intersaction points
        if(t1>0)
        {

            return List.of(new GeoPoint(this,ray.getPoint(t1)));
        }
        //in case of 1 intersaction points
        if(t2>0)
        {

            return List.of(new GeoPoint(this,ray.getPoint(t2)));
        }
        return null;

    }

//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
//
//        //calculating varieables for the final formula
//        Point3D P0 = ray.getP0();
//        Vector V = ray.getDir();
//        Point3D O = _center;
//        if(P0.equals(_center)){
//            return  List.of(_center.add(V.scale(radius)));
//        }
//        Vector U = O.subtract(P0);
//        double tm = V.dotProduct(U);
//        double d =alignZero(Math.sqrt(alignZero(U.lengthSquared() - tm * tm)));
//        //there is no intersaction
//        //
//        if(d>=radius){
//            return null;}
//        double th =alignZero(Math.sqrt(alignZero(radius*radius-d*d)));
//        double t1=alignZero(tm-th);
//        double t2=alignZero(tm+th);
//        // if P is on the surface---
//        if(isZero(th)){
//            return null;
//        }
//
//        //in case of 2 intersaction points
//        if(t1>0&&t2>0)
//        {
////            //the first point and the second point
//
//            return List.of(ray.getPoint(t1),ray.getPoint(t2));
//        }
//        //in case of 1 intersaction points
//        if(t1>0)
//        {
//
//            return List.of(ray.getPoint(t1));
//        }
//        //in case of 1 intersaction points
//        if(t2>0)
//        {
//
//            return List.of(ray.getPoint(t2));
//        }
//        return null;
//
//    }

}

