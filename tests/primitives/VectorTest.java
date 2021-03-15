
package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Test for Vector
 *
 * @author Achinoam and Yael
 */
class VectorTest {
    /**
     * Fields on which we will conduct the tests v1,v2
     */
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    @Test
    void testZeroPoint(){
        try { // test zero vector
            new Vector(0, 0, 0);
           fail("ERROR: zero vector does not throw an exception");
        } catch (IllegalArgumentException e) {
            out.println("Great!Vector 0 not created");
        }
    }
    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void dotProduct() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(-28, v1.dotProduct(v2), "dotProduct() wrong result ");

        // =============== Boundary Values Tests ==================
        // test if dot product return 0 in case that the vectors are ortogonal
        Vector v3 = new Vector(0, 3, -2);
        double result = v1.dotProduct(v3);
        assertTrue(result == 0, "dot product () result is not expected");
        result = v2.dotProduct(v3);
        assertTrue(result == 0, "dot product () result is not expected");


    }
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void crossProduct() {
        
        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (IllegalArgumentException e) {
        }

    }
    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     * check that add works corecct
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        Vector v3=new Vector(-1,-2,-3);
        Vector temp=v1.add(v2);
        assertEquals(v3,temp,"add() did not work correctly");
        // =============== Boundary Values Tests ==================
        //cannot create vector 0
        try{
            v1.add(v3);
        }
        catch (IllegalArgumentException e) {
            out.println("Great!Vector 0 not created");
        }
    }
    /**
     * Test method for {@link primitives.Vector#sutract(Vector)} (primitives.Vector)}.
     * check that subtract works corecct
     */
    @Test
    void sutract() {
        // ============ Equivalence Partitions Tests ==============
        Vector v3=new Vector(3,6,9);
        Vector temp=v1.sutract(v2);
        assertEquals(v3,temp,"subtract() did not work correctly");
        // =============== Boundary Values Tests ==================
      //cannot create vector 0
        try{
            v3.sutract(v3);
        }
        catch (IllegalArgumentException e) {
            out.println("Great!Vector 0 not created");
        }
    }
    /**
     * Test method for {@link primitives.Vector#scale(double)} (primitives.Vector)}.
     * check that scale works corecct
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============
        Vector vscale = v1.scale(-1);
        assertEquals(vscale, new Vector(-1, -2, -3), "scale() didn't worked correctly");
        // =============== Boundary Values Tests ==================
        //make sure that the scale will not give us vector 0
        try {
            v1.scale(0);
            fail("we can't create vector zero");
        } catch (Exception e) {
            assertTrue(true);
        }

    }
    /**
     * Test method for {@link Vector#lengthSquared()} ()} (primitives.Vector)}.
     * check that lengthsquare works corecct
     */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        if (!isZero(v1.lengthSquared() - 14))
            fail("ERROR: lengthSquared() wrong value");

    }
    /**
     * Test method for {@link Vector#length()} (primitives.Vector)}.
     * check that length works corecct
     */
    @Test
    void length() {

        // test length..
        // ============ Equivalence Partitions Tests ==============
        if (!isZero(new Vector(0, 3, 4).length() - 5))
            fail("ERROR: length() wrong value");
    }
    /**
     * Test method for {@link Vector#normalize()} ()} (primitives.Vector)}.
     * normalize() return the vector itself
     */
    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============
        // test vector normalization
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        if (!isZero(vCopyNormalize.length() - 1))
            out.println("ERROR: normalize() result is not a unit vector");
        Vector u = v.normalize();
        assertTrue(u==v,"ERROR: normalize() function did not change the vector itself");


    }
    /**
     * Test method for {@link Vector#normalize()} ()} (primitives.Vector)}.
     * normalized() return a new vector that is normalize now
     */
    @Test
    void normalized() {
        // test vector normalization
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        assertEquals (vCopy , vCopyNormalize,"ERROR: normalize() function creates a new vector");

        if (!isZero(vCopyNormalize.length() - 1))
            out.println("ERROR: normalize() result is not a unit vector");
        Vector u = v.normalized();
        v.normalize();
//        if (u == v)
//            out.println("ERROR: normalizated() function does not create a new vector");
        assertFalse(u==v,"ERROR: normalizated() function does not create a new vector");
//        if (u.equals(v))
//            out.println("GOOD");
        assertTrue(u.equals(v)," NOT GOOD");
    }
}
