package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * a class for some geometries together
 *
 * @author Yael and Achinoam
 */
public class Geometries implements Intersectable {
    /**
     * list of geometries
     */

    //comment:we chose linked list because we do only actions of iteration and addtion to the end
    // and not action such as access by index etc. and therefore we dont need arraylist
    List<Intersectable> _intersectables = null;

    /**
     * default constructor that initiate the list
     */
    public Geometries() {
        _intersectables = new LinkedList<>();
    }

    /**
     * @param geometries -the shape/s
     */
    public Geometries(Intersectable... geometries) {
        //sending the shapes for add in order to add them to the linked list
        this._intersectables = new LinkedList<>();
        add(geometries);
    }

    /**
     * an action that adding the shape to the collection
     *
     * @param geometries -the shape/s
     */
    public void add(Intersectable... geometries) {

//        for (Intersectable item : _intersectables) {
//            this._intersectables.add(item);
//        }
        Collections.addAll(_intersectables, geometries);

    }


    /**
     * @param ray for calculating the intersaction with the geometry
     * @return the list of intersaction points with the shapes
     * //if there is no intersaction-return null
     * //else-Activates the function for each shape according to its  implementation and acts accordingly
     */
//    @Override
//    public List<Point3D> findIntersections(Ray ray) {
//
//        List<Point3D> result = null;
//        for (Intersectable item : this._intersectables) {
//            // Activates the function for each shape according to its  implementation
//            List<Point3D> itemPoints = item.findIntersections(ray);
//            //if there is intersaction
//            if (itemPoints != null) {
//                if (result == null) {
//                    result = new LinkedList<>();
//
//                }
//                result.addAll(itemPoints);
//            }
//        }
//        return result;
//    }

//    @Override
//    public List<GeoPoint> findGeoIntersections(Ray ray) {
//        List<GeoPoint> intersections = null;
//        for (Intersectable geometry : _intersectables) {
//            var geoIntersections = geometry.findGeoIntersections(ray);
//            if (geoIntersections != null) {
//                if (intersections == null) {
//                    intersections = new LinkedList<>();
//
//                }
//                intersections.addAll(geoIntersections);
//            }
//            //if there are elements in geoIntersections – add them to intersections
//
//
//        }
//        return intersections;
//    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : _intersectables) {
            var geoIntersections = geometry.findGeoIntersections(ray,maxDistance);
            if (geoIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();

                }
                intersections.addAll(geoIntersections);
            }
            //if there are elements in geoIntersections – add them to intersections


        }
        return intersections;
    }
}
