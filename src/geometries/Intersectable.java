package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
/**
 * interface Intersectable
 * @author Yael and Achinoam
 *
 */

public interface Intersectable {
    /**
     * @param ray for calculating the intersaction with the geometry
     * @return list of  points of the  intersections  of the ray with the geometry.
     */
    List<Point3D> findIntersections(Ray ray);
}
