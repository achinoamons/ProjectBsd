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
public class Geometries extends Intersectable {
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

    //
    @Override
    protected void CreateBoundingBox() {
        minX = Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
        minZ = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        maxY = Double.MIN_VALUE;
        maxZ = Double.MIN_VALUE;
        for (Intersectable geo : _intersectables) {
            geo.createBox();
            if (geo.minX < minX)
                minX = geo.minX;
            if (geo.maxX > maxX)
                maxX = geo.maxX;
            if (geo.minY < minY)
                minY = geo.minY;
            if (geo.maxY > maxY)
                maxY = geo.maxY;
            if (geo.minZ < minZ)
                minZ = geo.minZ;
            if (geo.maxZ > maxZ)
                maxZ = geo.maxZ;

        }
        middleBoxPoint = getMiddlePoint();
    }

    /**
     * the function is calling the makeTree function after taking out all infinity
     * shapes to a separate list.<br>
     * after the function createGeometriesTreeRecursion was called and return the
     * binary tree list we add the infinity shape list to the head of the binary
     * tree.<br>
     * so geometries as all geometries in one binary tree.
     */
    public void createGeometriesTree() {
        LinkedList<Intersectable> shapesWhitOutBox = null;
        for (int i = 0; i < _intersectables.size(); ++i) {
            if (!_intersectables.get(i).finityShape) {
                if (shapesWhitOutBox == null)
                    shapesWhitOutBox = new LinkedList<Intersectable>();
                shapesWhitOutBox.add(_intersectables.remove(i));
                i--;
            }
        }
        if (!_intersectables.isEmpty())
            _intersectables = createGeometriesTreeRecursion(_intersectables);
        if (shapesWhitOutBox != null)
            _intersectables.addAll(0, shapesWhitOutBox);
    }

    /**
     * update box size after every new geometry we add to geometries list. create
     * the box that include both bodies
     */
    protected void updateBoxSize(Intersectable a, Intersectable b) {
        finityShape = true;
        minX = Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
        minZ = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        maxY = Double.MIN_VALUE;
        maxZ = Double.MIN_VALUE;
        minX = Math.min(a.minX, b.minX);
        minY = Math.min(a.minY, b.minY);
        minZ = Math.min(a.minZ, b.minZ);
        maxX = Math.max(a.maxX, b.maxX);
        maxY = Math.max(a.maxY, b.maxY);
        maxZ = Math.max(a.maxZ, b.maxZ);
        middleBoxPoint = getMiddlePoint();
    }

    /**
     * The function calculates the list that represents a binary tree of boxes
     * within boxes...<br>
     * inside each box there are two closely related shapes,<br>
     * used to accelerate the ray tracer,<br>
     * in case a ray does not cut the box all the boxes inside it are probably not
     * cut.
     *
     * @param finiteShapes the list of finite Shapes
     * @return a list of shapes present tree of geometries
     */
    List<Intersectable> createGeometriesTreeRecursion(List<Intersectable> finiteShapes) {
        if (finiteShapes.size() == 1)
            return finiteShapes;
        LinkedList<Intersectable> _newShapes = null;
        while (!finiteShapes.isEmpty()) {
            Intersectable first = finiteShapes.remove(0), nextTo = finiteShapes.get(0);
            double minD = first.middleBoxPoint.distance(nextTo.middleBoxPoint);
            int min = 0;
            for (int i = 1; i < finiteShapes.size(); ++i) {
                if (minD == 0)
                    break;
                double temp = first.middleBoxPoint.distance(finiteShapes.get(i).middleBoxPoint);
               //swap
                if (temp < minD) {
                    minD = temp;
                    nextTo = finiteShapes.get(i);
                    min = i;
                }
            }
            if (_newShapes == null)
                _newShapes = new LinkedList<Intersectable>();
            finiteShapes.remove(min);
            Geometries newGeo = new Geometries(first, nextTo);
            newGeo.updateBoxSize(first, nextTo);
            _newShapes.add(newGeo);
            if (finiteShapes.size() == 1)
                _newShapes.add(finiteShapes.remove(0));
        }
        return createGeometriesTreeRecursion(_newShapes);
    }
    //
}
