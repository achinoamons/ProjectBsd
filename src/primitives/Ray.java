package primitives;

import geometries.Intersectable.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Achinoam and Yael
 * /**
 *  * "Half-straight - all the points on the line that are on one side of the point given on the line called the beginning / beginning / beginning of the fund):
 */
public class Ray {
    Point3D _p0;
    Vector _dir;

    /**
     * constructor
     *
     * @param p0  point
     * @param dir vector
     */
    public Ray(Point3D p0, Vector dir) {
        this._p0 = p0;
        this._dir = dir;
        this._dir = this._dir.normalize();
    }

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
}
