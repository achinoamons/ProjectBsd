package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
    //---אין צורך בסוף---יש מהירושה (שמתי בנאי חדש כדי שיכיר תנק לפונקציה
//    public Triangle(Point3D ...vertices ) {
//       super(vertices);
//   }
    @Override
    public List<Point3D> findIntersections(Ray ray) {

        List<Point3D> list1 = plane.findIntersections(ray);
        //IF THE PLANE HAS NO INTERSACTION POINTS-RETURN NULL
        if (list1 == null)
            return null;
        Vector v1 = vertices.get(0).subtract(ray.getP0());
        Vector v2 = vertices.get(1).subtract(ray.getP0());
        Vector v3 = vertices.get(2).subtract(ray.getP0());

        Vector N1 = v1.crossProduct(v2).normalize();
        Vector N2 = v2.crossProduct(v3).normalize();
        Vector N3 = v3.crossProduct(v1).normalize();

        double t1 = ray.getDir().dotProduct(N1);
        if(isZero(t1))
            return null;
        double t2 = ray.getDir().dotProduct(N2);
        if(isZero(t2))
            return null;
        double t3 = ray.getDir().dotProduct(N3);
        if(isZero(t3))
            return null;
      //if they all have the same sign-there is an intersaction
        if(alignZero(t1) >0 && alignZero(t2)> 0 && alignZero(t3) > 0 || alignZero(t1)<0 && alignZero(t2) < 0 && alignZero(t3) < 0)
        {
//            List<Point3D> result = new LinkedList<>();
////            for (Point3D p : list1)
////            {
////                result.add(p);
////            }
//            result.addAll(list1);
//            return result;
             return plane.findIntersections(ray);
        }

        return null;
    }

}
