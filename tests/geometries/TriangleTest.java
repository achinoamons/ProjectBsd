package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @Test
    void testnormal(){
        // ============ Equivalence Partitions Tests ==============
        Triangle tr=new Triangle(new Point3D(0,0,1),new Point3D(1,2,1),new Point3D(1,0,2));
        assertEquals(tr.getNormal(null),new Vector(0.6666666666666666,-0.3333333333333333,-0.6666666666666666));
    }

}