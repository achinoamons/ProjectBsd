package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {
        // ============ Equivalence Partitions Tests ==============
        //TC01:A point in the middle of the list is closest to the beginning of the fund
        Ray ray = new Ray(new Point3D(1,3,0), new Vector(0,1,0));

        List<Point3D> list = new LinkedList<Point3D>();
        list.add(new Point3D(0,5,0));
        list.add(new Point3D(0,4,0));
        list.add(new Point3D(0, 3,0));
        list.add(new Point3D(0,2,0));
        list.add(new Point3D(0,1,0));
        assertEquals(list.get(2), ray.findClosestPoint(list));
        // =============== Boundary Values Tests ==================
        //TC11:THE LIST IS EMPTY-return null
        Ray ray1 = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));

        List<Point3D> list1 = null;
        assertNull(ray.findClosestPoint(list1), "try again");
        //TC12:The first point is closest to the beginning of the foundation
        Ray ray2 = new Ray(new Point3D(1,5,0), new Vector(0,1,0));
        assertEquals(list.get(0), ray2.findClosestPoint(list));
        //TC13: The last point is closest to the beginning of the foundation

        Ray ray3 = new Ray(new Point3D(1,1,0), new Vector(0,1,0));
        assertEquals(list.get(4), ray3.findClosestPoint(list));
    }
}