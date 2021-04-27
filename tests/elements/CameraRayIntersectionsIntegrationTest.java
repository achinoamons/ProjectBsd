package elements;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * a class for integration tests of camera,view plane and geometries
 * @ author Yael and Achinoam
 */
public class CameraRayIntersectionsIntegrationTest {
    /**
     * a function that help us calculte the sum of intersaction in the integration tests
     *
     * @param cam             is the camera
     * @param geometry-the    relevant shape
     * @param expectedAns-the amount of expected intersaction
     */
    private void countIntersactions(Camera cam, Intersectable geometry, int expectedAns) {
        int counter = 0;
        //values according to the presentation
        cam.setViewPlaneSize(3, 3).setDistance(1);
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                //intersaction is the list of intersaction point/s with the specific geometry
                var intersactions = geometry.findIntersections(cam.constructRayThroughPixel(3, 3, j, i));
                counter += intersactions == null ? 0 : intersactions.size();
            }
        assertEquals(expectedAns, counter, "Wrong amount of intersactions");
    }


    @Test
    public void CameraRaySphereIntersactions() {
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));
        //TC01-2 intersaction points-small sphere
        Sphere sp = new Sphere(new Point3D(0, 0, -3), 1d);
        countIntersactions(cam1, sp, 2);

        //TC02-18 intersaction points-big sphere
        Sphere sp1 = new Sphere(new Point3D(0, 0, -2.5), 2.5d);
        countIntersactions(cam2, sp1, 18);

        //TC03-10 intersaction points-medium sphere
        Sphere sp2 = new Sphere(new Point3D(0, 0, -2), 2);
        countIntersactions(cam2, sp2, 10);

        //TC04-9 intersaction points-the camera is in the sphere
        Sphere sp3 = new Sphere(new Point3D(0, 0, -1), 4);
        countIntersactions(cam2, sp3, 9);

        //TC05-0 intersaction points-the sphere is behind the camera
        Sphere sp4 = new Sphere(new Point3D(0, 0, 1), 0.5);
        countIntersactions(cam1, sp4, 0);


    }

    @Test
    public void CameraRayTriangleIntersactions() {
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));

        //TC01-1 intersaction points-small TRIANGLE
        Triangle tr1 = new Triangle(new Point3D(0,1,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));
        countIntersactions(cam2, tr1, 1);

        //TC02-2 intersaction points-big TRIANGLE
        Triangle tr2 = new Triangle(new Point3D(0,20,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));
        countIntersactions(cam2, tr2, 2);

    }
    @Test
    public void CameraRayPlaneIntersactions() {
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));
        // TC01: Plane is against camera 9 points
        Plane pl1=new Plane(new Point3D(0,0,-4),new Vector(0,0,1));
        countIntersactions(cam1, pl1, 9);
        // TC02:  Plane with small angle 9 points
        //(the plane formula: y+2z=-10)
        Plane pl2 =new Plane (new Point3D(2,-2,-4), new Vector(0,1,2));
        countIntersactions(cam2, pl2, 9);

        // TC03:  Plane is  parallel to the low rays 6 points
        //(the plane formula: y+z=-5)
        Plane pl3 =new Plane (new Point3D(2,-2,-3), new Vector(0,1,1));
        countIntersactions(cam2, pl3, 6);

        // TC03:  Plane is  behind the camera
        //we added a test
        Plane pl4 =new Plane(new Point3D(0,0,4),new Vector(0,0,1));

        countIntersactions(cam1, pl4, 0);
    }
}
