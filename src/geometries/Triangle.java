package geometries;

import primitives.Point3D;

/**
 * Triangle class is Polygon with vertices
 *
 * @author Achinoam and Yael
 */
public class Triangle extends Polygon {
    /**
     * Triangle constractor based on 3 point, the points are sent to the father constractor
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

}
