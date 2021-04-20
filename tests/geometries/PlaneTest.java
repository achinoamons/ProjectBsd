package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PlaneTest {
    /**
     *  Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //test for normal plane
        Point3D p1= new Point3D(1,2,0);
        Point3D p2= new Point3D(-4,7,0);
        Point3D p3= new Point3D(1,0,5);
        Plane p= new Plane(p1,p2, p3);
        Vector v1=new Vector(p1.subtract(p2).getHead());
        Vector v2=new Vector(p2.subtract(p3).getHead());
        Vector v3=new Vector(p3.subtract(p1).getHead());
        Vector n=p.getNormal(p1);
        //check that the vector n is orthogonal for all the vectors in the plane
        assertTrue( isZero(v1.dotProduct(n)),"ERROR: Bad normal to plane");
        assertTrue( isZero(v2.dotProduct(n)),"ERROR: Bad normal to plane");
        assertTrue( isZero(v3.dotProduct(n)),"ERROR: Bad normal to plane");
        try {
            new Plane(new Point3D(1,2,3),new Point3D(2,4,6),new Point3D(4,8,12)).getNormal(p1);
            fail("GetNormal() should throw an exception, but it failed");
        } catch (Exception e) {}

    }

    @Test
    void findIntsersections() {

        Plane plane = new Plane(new Point3D(1, 0, 0), new Vector (0, 0, 1));
        // ============ Equivalence Partitions Tests ==============
        // TC01:Ray intersects the plane (1 point)
        List<Point3D> result = plane.findIntersections(new Ray(new Point3D(-2, 0.5, -1), new Vector(1, 1, 1)));
        assertEquals( 1,result.size(),"Wrong number of points");
        assertEquals(List.of(new Point3D(-1, 1.5, 0)), result,"Ray intersects the plane");


        // TC02:Ray does not intersect the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(3, 1, 0.5), new Vector(1, 1, 1))),"Ray not intersects the plane");

        // =============== Boundary Values Tests ==================

        // ****Group: Ray is parallel to the plane

        // TC11: Ray included in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(3, 1, 0), new Vector(1, 1, 0))),"Ray in the plane");
        // TC12: Ray not included in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(3, 1, 0.5), new Vector(1, 1, 0))),"Ray not included in the plane");

        // ****Group: Ray is orthogonal to the plane
        //TC13: Ray starts before the plane (1 points)
        result = plane.findIntersections(new Ray(new Point3D(-4, 3, -2), new Vector(0, 0, 1)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(new Point3D(-4, 3, 0)), result,"Ray is orthogonal to the plane and starts before the plane");

        //TC14: Ray starts in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(3, 1, 0), new Vector(0, 0, 1))),"Ray is orthogonal to the plane and starts in the plane");
        //TC15: Ray starts after the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(3, 1, 1), new Vector(0, 0, 1))),"Ray is orthogonal to the plane and starts after the plane");

        // ****Group: Special cases
        //TC16:Ray is neither orthogonal nor parallel to and begins at the plane (0 options)
        assertNull(plane.findIntersections(new Ray(new Point3D(3, 1, 0), new Vector(0, 1, 1))),"Ray begins at the plane");
        //TC17: Ray is neither orthogonal nor parallel to the plane and begins in
        //the same point which appears as reference point in the plane (Q)
        assertNull(plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 2))),"Ray begins at the plane");



    }

}