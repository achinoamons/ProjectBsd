package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class represent A point in space and  a vertical vector
 * system
 *
 * @author Achinoam and Yael
 */
public class Plane extends Geometry {
    /**
     * Point and vector
     */
    final Point3D _q0;
    final Vector _normal;
    private static final double DELTA = 0.1;

    /**
     * Seconed constractor, gets vector and point
     *
     * @param q0     point
     * @param normal vector
     */
    public Plane(Point3D q0, Vector normal) {
        _q0 = q0;
        _normal = normal.normalized();
    }

    /**
     * Plane constractor, gets 3 points
     *
     * @param p1
     * @param p2
     * @param p3 the constructor also calculate the normal of the plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);

        N.normalize();

        _normal = N;
    }

    public Vector getNormal() {

        return _normal;
    }


    public Point3D getQ0() {
        return _q0;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        //data of the ray
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector n = _normal;
        //if the intersaction point is p0-we dont include it
        if (_q0.equals(P0)) {
            return null;
        }

        Vector P0_Q0 = _q0.subtract(P0);

        double numerator = alignZero(n.dotProduct(P0_Q0));

        //
        if (isZero(numerator)) {
            return null;
        }

        //mone
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if (isZero(nv)) {
            return null;
        }
        //if everything good-calculate p
        double t = alignZero(numerator / nv);

        if (t <= 0) {
            return null;
        }
        //to check if it is the range
        if ( (alignZero(t - maxDistance) <= 0)) {
            Point3D P = ray.getPoint(t);

            return List.of(new GeoPoint(this, P));
        }
        return null;
    }


//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
////        //data of the ray
////        Point3D P0 = ray.getP0();
////        Vector v = ray.getDir();
////        //if the intersaction point is q0
////        if (_q0.equals(P0)) {
////            //Returns an unmodifiable list containing one element-q0
////            return List.of(_q0);
////
////        }
////        double nv = _normal.dotProduct(v);
////        //check if 0 in the denominator-n and v orthogonal
////        //the ray is lying on the plane
////        if (isZero(nv)) {
////            return null;
////        }
////        //if everything good-calculate p
////        double t = _normal.dotProduct(_q0.subtract(P0));
////        t /= nv;
////        Point3D p = P0.add(v.scale(t));
////        return List.of(p);
//
//        //data of the ray
//        Point3D P0 = ray.getP0();
//        Vector v = ray.getDir();
//
//        Vector n = _normal;
//        //if the intersaction point is p0-we dont include it
//        if (_q0.equals(P0)) {
//            return null;
//        }
//
//        Vector P0_Q0 = _q0.subtract(P0);
//
//        double numerator = alignZero(n.dotProduct(P0_Q0));
//
//        //
//        if (isZero(numerator)) {
//            return null;
//        }
//
//        //mone
//        double nv = alignZero(n.dotProduct(v));
//
//        // ray is lying in the plane axis
//        if (isZero(nv)) {
//            return null;
//        }
//        //if everything good-calculate p
//        double t = alignZero(numerator / nv);
//
//        if (t <= 0) {
//            return null;
//        }
//        Point3D P = ray.getPoint(t);
//
//        return List.of(P);
//    }

//
@Override
protected void CreateBoundingBox() {
    double x = _normal.getHead().getX(), y = _normal.getHead().getY(), z = _normal.getHead().getZ();
    if (y == 0 && z == 0) {
        minX = maxX = _q0.getX();
        minX -= DELTA;
        maxX += DELTA;
    } else {
        minX = Double.NEGATIVE_INFINITY;
        maxX = Double.POSITIVE_INFINITY;
    }
    if (x == 0 && z == 0) {
        minY = maxY = _q0.getY();
        minY -= DELTA;
        maxY += DELTA;
    } else {
        minY = Double.NEGATIVE_INFINITY;
        maxY = Double.POSITIVE_INFINITY;
    }
    if (x == 0 && y == 0) {
        minZ = maxZ = _q0.getZ();
        minZ -= DELTA;
        maxZ += DELTA;
    } else {
        minZ = Double.NEGATIVE_INFINITY;
        maxZ = Double.POSITIVE_INFINITY;
    }
}
//
}

