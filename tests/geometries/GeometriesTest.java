package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntersections() {
        Geometries g = new Geometries();

        // =============== Boundary Values Tests ==================
        //TC11: Empty list
        assertNull(  g.findIntersections(new Ray(new Point3D(3, 1, 0.5), new Vector(1, 1, 0))),"list Empty");

        g._intersectables.add(new Triangle(new Point3D(-1, 0.5, -6), new Point3D(-1, 0, -6), new Point3D(2, 0, -6)));
        g._intersectables.add(new Plane(new Point3D(1, 0, -4), new Vector (0, 0, 1)));
        g._intersectables.add(new Sphere(new Point3D(0, 0, 1), 5));

        //TC12: No shape cut
        assertNull( g.findIntersections(new Ray(new Point3D(0,0,8), new Vector(1,0, 0))),"Ray not included in the plane");
        //assertNull( g.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))),"Ray not included in the plane");
        //TC13: One shape cut---the sphere cut 2 times
        List<Point3D> l = g.findIntersections(new Ray(new Point3D(6,-6,4), new Vector(-12,12,0)));
        assertEquals( 2, l.size(),"Ray not included in the plane");
        //TC14: All shapes cut--in addition pay attention that the sphere cut 2 times
        l = g.findIntersections(new Ray(new Point3D(-0.7, 0.2, -8), new Vector(0, 0, 1)));
        assertEquals( 4, l.size(),"Ray not included in the plane");


        // ============ Equivalence Partitions Tests ==============
        //TC01: Some shapes are cut---THE TRIANGLE has no intersaction because the intersaction is on edge
       //in addition pay attention that the sphere cut 2 times
       l = g.findIntersections(new Ray(new Point3D(1, 0, -8), new Vector(0, 0, 1)));
        assertEquals( 3, l.size(),"Ray not included in the plane");

    }
}