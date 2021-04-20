package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @Test
    void testnormal(){
        // ============ Equivalence Partitions Tests ==============
        Triangle tr=new Triangle(new Point3D(0,0,1),new Point3D(1,2,1),new Point3D(1,0,2));
        assertEquals(tr.getNormal(null),new Vector(0.6666666666666666,-0.3333333333333333,-0.6666666666666666));
    }

    @Test
    void findIntersections() {
        Triangle triangle = new Triangle(new Point3D(-1, 3, 0), new Point3D(-1, 0, 0), new Point3D(2, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01:Ray inside triangle (1 ptions)
        List<Point3D> result = triangle.findIntersections(new Ray(new Point3D(-1, 8, -5), new Vector(1, -7, 5)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(new Point3D(0, 1, 0)), result,"Ray intersects the triangle");

        //TC02:Ray outside against edge (0 ptions)
        assertNull(triangle.findIntersections(new Ray(new Point3D(-3, -3, -1), new Vector(1, 5, 1))),"Ray not included in the triangle");
        //TC03:Ray outside against vertex (0 ptions)
        assertNull(triangle.findIntersections(new Ray(new Point3D(-3, -3, -1), new Vector(1, 2, 1))),"Ray not included in the triangle");

        // =============== Boundary Values Tests ==================

        //TC11:Ray on edge (0 ptions)
        assertNull(triangle.findIntersections(new Ray(new Point3D(-2, -3, -1), new Vector(1, 4, 1))),"Ray not included in the triangle");

        //TC12:Ray in vertex (0 ptions)
        assertNull(triangle.findIntersections(new Ray(new Point3D(-2, -3, -1), new Vector(1, 6, 1))),"Ray not included in the triangle");

        //TC13: Ray on edge's continuation (0 ptions)
        assertNull(triangle.findIntersections(new Ray(new Point3D(-2, -3, -1), new Vector(-1, 8, 1))),"Ray not included in the triangle");


    }
}