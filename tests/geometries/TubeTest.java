package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @Test
    void getNormal() {

        Tube t=new Tube(new Ray(new Point3D(0,0,0),new Vector(1,0,0)),3);
        // ============ Equivalence Partitions Tests ==============
        //TEST FOR TUBE PLANE
        Vector v=new Vector(0.0,0.4472135954999579,0.8944271909999159);
        assertTrue(v.equals(t.getNormal(new Point3D(2,4,8))),"getNormal() result is not expected");
       //assertEquals(v,t.getNormal(new Point3D(2,4,8)),"");
    }
}