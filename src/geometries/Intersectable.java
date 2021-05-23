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

public interface Intersectable {
    /**
     * inner static class that help us know for which shape a point is belong to
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * constructor that initiate the field
         * @param geometry is the shape
         * @param point is the point (that it belongs to)
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

    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }

    default List<GeoPoint> findGeoIntersections (Ray ray){
        return findGeoIntersections(ray,Double.POSITIVE_INFINITY);
    }

    List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

}
