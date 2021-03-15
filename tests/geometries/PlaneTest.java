package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

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
}