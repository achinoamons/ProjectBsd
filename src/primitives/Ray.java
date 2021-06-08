package primitives;

import elements.LightSource;
import geometries.Intersectable.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.random;

/**
 * @author Achinoam and Yael
 * /**
 *  * "Half-straight - all the points on the line that are on one side of the point given on the line called the beginning / beginning / beginning of the fund):
 */
public class Ray {
    private static final double DELTA = 0.1;

    private final Point3D _p0;
    private final Vector _dir;

    /**
     * constructor
     *
     * @param p0  point
     * @param dir vector
     */
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        //this._dir = dir;
        //this._dir = this._dir.normalize();
        _dir=dir.normalized();
    }

    /**
     * constructor that creata a new ray-Building a ray with moving a point
     * @param point for _p0
     *
     * @param n normal
     *
     *
     */

        public Ray(Point3D point, Vector v , Vector n) {

        //mooving a little bit the start point of the ray
        Vector move = n.scale(n.dotProduct(v) > 0 ? DELTA : - DELTA);
        _p0=point.add(move);
        _dir=v;
    }
//    public Ray(Point3D point, LightSource lightsource, Vector n, double delta) {
//       //creating vector in the oppsite direction of the lightsource vector l(GetL)
//        Vector l=lightsource.getL(point).scale(-1);
//        //mooving a little bit the start point of the ray
//        Vector move = n.scale(n.dotProduct(l) > 0 ? delta : - delta);
//        _p0=point.add(move);
//        _dir=l;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    @Override
    public String toString() {
        return "" + _p0.toString() + ":" + _dir.toString();
    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    public Point3D getPoint(double t) {

       return  _p0.add(_dir.scale(t));
    }


    /**
     *
     * @param listOfPoints for calculating distance between each point to the ray
     * @return the point with the closest point to the ray
     */
    public Point3D findClosestPoint(List<Point3D> listOfPoints){
       //if the list is empty
        if(listOfPoints==null){
            return null;
        }
        Point3D result=null;
       //double closestDistane=listOfPoints.get(0).distance(_p0);//זה הכי יפה בעיננו//
         double closestDistane = Double.MAX_VALUE;

        for (Point3D p :listOfPoints){
            double tmp=p.distance(_p0);
            if(tmp<=closestDistane){

                closestDistane=tmp;
                result=p;
            }
        }
        return result;
    }




    /**
     *
     * @param listOfPoints for calculating distance between each point to the ray
     * @return the point with the closest point to the ray
     */
    public GeoPoint findClosestGeoPoint  (List<GeoPoint> listOfPoints){
        //if the list is empty
        if(listOfPoints==null){
            return null;
        }
        GeoPoint result=null;
        //double closestDistane=listOfPoints.get(0).distance(_p0);//זה הכי יפה בעיננו//
        double closestDistane = Double.MAX_VALUE;

        for (GeoPoint geo :listOfPoints){
            double tmp=geo.point.distance(_p0);
            if(tmp<=closestDistane){

                closestDistane=tmp;
                result=geo;
            }
        }
        return result;
    }


    //////////////////////


    /**
     *
     *
     * @param centerOfCircle center
     * @param radius         radius of circle
     * @return random point on the circle
     */
    //anti
//    public Point3D randomPointOnRadius(Point3D centerOfCircle, double radius) {
//        Vector firstNormal = _dir.createVerticalVector();
//        Vector secondNormal = firstNormal.crossProduct(_dir).normalize();
//        Point3D randomCirclePoint = centerOfCircle;
//        double x = 0, y = 0, r = 0;
//        x = random(-1, 1);
//        y = Math.sqrt(1 - x * x);
//        r = random(-radius, radius);
//        x = alignZero(x * r);
//        y = alignZero(y * r);
//        if (x != 0)
//            randomCirclePoint = randomCirclePoint.add(firstNormal.scale(x));
//        if (y != 0)
//            randomCirclePoint = randomCirclePoint.add(secondNormal.scale(y));
//        return randomCirclePoint;
//    }
    /**
     * **for antiAliasing**<br>
     * Gets the num of rays and the area's degrees where all the rays will be
     *
     * @param NumOfRays num of additional rays
     * @param radius    of the area for all the rays
     * @param distance  of the radius circle from the head of the ray
     * @return rays are randomly whitout the ray itself
     */
    //anti ali
//    public List<Ray> raySplitter(int NumOfRays, double radius, double distance) {
//        if(NumOfRays==1){return List.of(this);}
//        List<Ray> splittedRays = new LinkedList<>();
//        Point3D centerCirclePoint = null;
//
//        try {
//            centerCirclePoint = this.getPoint(distance);
//        } catch (Exception e) {
//            centerCirclePoint = _p0;
//        }
//        Point3D randomCirclePoint = null;
//        for (int i = 0; i < NumOfRays; i++) {
//            randomCirclePoint = randomPointOnRadius(centerCirclePoint, radius);
//            Vector v = randomCirclePoint.subtract(_p0);
//            splittedRays.add(new Ray(_p0, v));
//        }
//        return splittedRays;
//    }
    /**
     * **for Depth Of Filed**<br>
     * Creates a beam of rays
     *
     * @param NumOfRays  num of additional rays
     * @param size       of the area for all the rays--shutter size
     * @param distance   of the square from the head of the ray
     * @param focalPoint target point, all the rays will go through this point
     * @return A list of random rays passing through the given square including the
     *         ray itself
     */
    public List<Ray> raySplitter(int NumOfRays, double size, double distance, Point3D focalPoint) {
        List<Ray> splittedRays = new LinkedList<>();
        for (int i = 0; i < NumOfRays; i++) {
            Point3D point3d = _p0.randomPointOnSquare(_dir, size, size);
            Vector v = focalPoint.subtract(point3d);
            splittedRays.add(new Ray(point3d, v));
        }
        splittedRays.add(this);
        return splittedRays;
    }
}
