package primitives;

import org.junit.jupiter.api.Test;

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
    void dotProduct() {

        assertEquals(-28,v1.dotProduct(v2),"dotProduct() wrong result ");
    }

    @Test
    void crossProduct() {



        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals( v1.length() * v3.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (IllegalArgumentException e) {
        }

    }

    @Test
    void add() {
        Vector v3 = new Vector(-1, -2, -3);

        // ============ Equivalence Partitions Tests ==============
        Vector temp = v1.add(v2);
        assertEquals( temp, v3,"add() result is not expected");

        // =============== Boundary Values Tests ==================
        try {
            v1.add(v3);

            fail("add() for parallel vectors does not throw an exception");
        } catch (Exception e)
        {
            assertTrue(true);}
    }

    @Test
    void sutract() {

    }

    @Test
    void scale() {

    }

    @Test
    void lengthSquared() {

    }

    @Test
    void length() {
    }

    @Test
    void normalize() {
    }

    @Test
    void normalized() {
    }
}