package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @Test
    void getNormal() {

        Tube t = new Tube(new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 0)), 3);
        // ============ Equivalence Partitions Tests ==============
        //TEST FOR TUBE PLANE


        Vector v1 = new Vector(0.0, 0.7071067811865475, 0.7071067811865475);

        assertEquals(v1, t.getNormal(new Point3D(0, Math.sqrt(9 / 2), Math.sqrt(9 / 2))), "getNormal() result is not expected");

//        // =============== Boundary Values Tests ==================
        //When connecting the point to the head of the ray of the cylinder axis produces a right angle with the axis - the point "is in front of the head of the ray")
        Vector v = new Vector(0, 1, 0);
        assertTrue(v.equals(t.getNormal(new Point3D(0, 3, 0))), "getNormal() result is not expected");
        assertEquals(v, t.getNormal(new Point3D(0, 3, 0)), "");
    }
}