package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    /**
     *  Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Sphere sp=new Sphere(new Point3D(0,0,0),5);
        Vector tmp=new Vector(0,1,0);
        assertTrue(  tmp.equals(sp.getNormal(new Point3D(0, 4, 0))),"getNormal() result is not expected");
    }
}