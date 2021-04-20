package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SphereTest {
    /**
     *  Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Sphere sp=new Sphere(new Point3D(0,0,0),5);
        Vector tmp=new Vector(0,1,0);
        assertTrue(  tmp.equals(sp.getNormal(new Point3D(0, 5, 0))),"getNormal() result is not expected");
    }

    @Test
    void findIntsersections() {
        /**
         * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
         */

            Sphere sphere = new Sphere( new Point3D(1, 0, 0),1d);

            // ============ Equivalence Partitions Tests ==============

            // TC01: Ray's line is outside the sphere (0 points)
            assertNull(
                    sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))),"Ray's line out of sphere");

            // TC02: Ray starts before and crosses the sphere (2 points)
            Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
            Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
            List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                    new Vector(3, 1, 0)));
            assertEquals( 2, result.size(),"Wrong number of points");
            if (result.get(0).getX() > result.get(1).getX())
                result = List.of(result.get(1), result.get(0));
            assertEquals( List.of(p1, p2), result,"Ray crosses sphere");

            // TC03: Ray starts inside the sphere (1 point)

//        Sphere sphere1= new Sphere( new Point3D(1, 1, 1),4d);
//        List<Point3D> result1 = sphere1.findIntersections(new Ray(new Point3D(2,2,2), new Vector(1,1,1)));
//        assertEquals(1, result1.size(),"Wrong number of points");

       ///////////////////////////////////

        List<Point3D> result3 = sphere.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, result3.size(),"Wrong number of points");
        assertEquals(result3.get(0),new Point3D(2,0,0));
        // TC04: Ray starts after the sphere (0 points)
        assertEquals( null, sphere.findIntersections(new Ray(new Point3D(2.2, 0, 0), new Vector(1, 1, 0))),"Ray's line out of sphere");
        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 0)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(new Point3D(0.9999999999999998, 0.9999999999999998, 0), result.get(0),"Ray in sphere");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(  sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0.6, 0.8, 0))),"Ray's line out of sphere");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 0, 0)));
        assertEquals( 2, result.size(),"Wrong number of points");
        assertEquals(List.of(new Point3D(0, 0, 0), new Point3D(2, 0, 0)), result,"Ray in sphere");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 0)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(new Point3D(2, 0, 0)), result,"Ray in sphere");

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(new Point3D(2, 0, 0)), result,"Ray start center");
        // TC16: Ray starts at the center (1 points)

        result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(new Point3D(1, 1, 0)), result,"Ray start center");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(  sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))),"Ray start at sphere and goes outside");
        // TC18: Ray starts after sphere (0 points)
        assertNull( sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(1, 0, 0))),"Ray start after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull( sphere.findIntersections(new Ray(new Point3D(2, 0, -1), new Vector(0, 0, 1))),"Ray start before tanget point");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0, 0, 1))),"Ray start at tanget point" );
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 0, 1), new Vector(0, 0, 1))),"Ray start after tanget point" );

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(0, 0, 1))),"Ray's line is outside, ray is orthogonal to ray start to sphere's center line");


    }
}