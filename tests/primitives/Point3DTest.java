package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {
   Point3D p1=new Point3D(0.0d,0.5d,1.0d);
    Point3D p2=new Point3D(0.000000000000000001d,0.49999999999999999d,1.0d);

    @Test
    void testEquals() {
        assertEquals(p1,p2);
    }

    @Test
    void testToString() {
        System.out.println("the first point is: "+p1);
        System.out.println("the second point is: "+p2);
    }

    @Test
    void distance() {
        assertEquals(0,p1.distance(p2));
    }
}