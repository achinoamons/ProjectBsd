package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;


/**
 * interface Intersectable
 * @author Yael and Achinoam
 *
 */

public abstract class Intersectable {
   //
    /**
     * minX, maxX, minY, maxY, minZ, maxZ<br>
     * To create a box that corresponds to the axes we need 6 variables,<br>
     * because there are 8 points of the box double 3 coordinates and each
     * coordinate appears 4 times the same<br>
     * so it is possible to divide 3 * 8/4 = 6
     */
    protected double minX, maxX, minY, maxY, minZ, maxZ;
    /**
     * middle Box Point, the inner middle point of the box
     */
    protected Point3D middleBoxPoint;
    /**
     * if the shape are finite shape like sphere or cylinder = true<br>
     * other wise if shape are like plane or tube it mean infinity = false
     */
    protected boolean finityShape = false;
    /**
     * To give an option with or without acceleration of speed bounding volume
     * Hierarchy<br>
     * if we wont whit acceleration = true , whitout acceleration = false
     */
    protected boolean BVHactivated = false;

    /**
     *
     * Create box for the shape <br>
     * set the miniX value to the minimum coordinate x of the shape or collection of
     * shape <br>
     * set the miniY value to the minimum coordinate y of the shape or collection of
     * shape<br>
     * set the miniZ value to the minimum coordinate z of the shape or collection of
     * shape<br>
     */
    protected abstract void CreateBoundingBox();

    /**
     * creating boxes for all shapes in the geometries list<br>
     * and setting the bounding to be true
     */
    public void createBox() {
        BVHactivated = true;
        CreateBoundingBox();
    }

    /**
     * Function for finding the midpoint inside the box
     *
     * @return the center inner point of the box
     */
    public Point3D getMiddlePoint() {
        return new Point3D(minX + ((maxX - minX) / 2), minY + ((maxY - minY) / 2), minZ + ((maxZ - minZ) / 2));
    }

    /**
     * Extremely fast algorithms<br>
     * for checking whether a ray cuts a box
     *
     * @param ray we want to test whether it is cutting or not the box
     * @return true if intersect false if not
     */
    public boolean isIntersectWithTheBox(Ray ray) {
        Point3D head = ray.getDir().getHead();
        Point3D p = ray.getP0();
        double temp;

        double dirX = head.getX(), dirY = head.getY(), dirZ = head.getZ();
        double origX = p.getX(), origY = p.getY(), origZ = p.getZ();

        // Min/Max starts with X
        double tMin = (minX - origX) / dirX, tMax = (maxX - origX) / dirX;
        if (tMin > tMax) {
            temp = tMin;
            tMin = tMax;
            tMax = temp;
        } // swap

        double tYMin = (minY - origY) / dirY, tYMax = (maxY - origY) / dirY;
        if (tYMin > tYMax) {
            temp = tYMin;
            tYMin = tYMax;
            tYMax = temp;
        } // swap
        if ((tMin > tYMax) || (tYMin > tMax))
            return false;
        if (tYMin > tMin)
            tMin = tYMin;
        if (tYMax < tMax)
            tMax = tYMax;

        double tZMin = (minZ - origZ) / dirZ, tZMax = (maxZ - origZ) / dirZ;
        if (tZMin > tZMax) {
            temp = tZMin;
            tZMin = tZMax;
            tZMax = temp;
        } // swap
        return tMin <= tZMax && tZMin <= tMax;
    }


   //

    /**
     * inner static class that help us know for which shape a point is belong to
     */

    public static class GeoPoint {
        /**
         *
         */
        public Geometry geometry;
        public Point3D point;

        /**
         * constructor that initiate the field
         * @param geometry is the shape
         * @param point is the intersection  point (that it belongs to)
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }


    }
    /**
     * @param ray for calculating the intersaction with the geometry
     * @return list of  points of the  intersections  of the ray with the geometry.
     * there is default implementation to the func
     */

    //List<Point3D> findIntersections(Ray ray);

    public List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }

    public List<GeoPoint> findGeoIntersections (Ray ray){
        return findIntersactionBound(ray);
    }

   abstract List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

    public List<GeoPoint> findIntersactionBound(Ray ray){
        //if there was an intersaction with the box or not
        return BVHactivated&&!isIntersectWithTheBox(ray)?null:findGeoIntersections(ray,Double.POSITIVE_INFINITY);
    }

    public List<GeoPoint> findIntersactionBound(Ray ray,double maxDistance){
        //if there was an intersaction with the box or not
        return BVHactivated&&!isIntersectWithTheBox(ray)?null:findGeoIntersections(ray,maxDistance);
    }

}
