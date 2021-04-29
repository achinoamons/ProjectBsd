//package renderer;
//
//import primitives.Color;
//import primitives.Point3D;
//import primitives.Ray;
//import scene.Scene;
//
//import java.util.List;
//
//public class RayTracerBasic extends RayTracerBase{
//    //A CONSTRUCTOR that send to the father
//    public RayTracerBasic(Scene scene) {
//        super(scene);
//    }
//
//    /**
//     *
//     * @param ray get a ray and find intersactionspoints
//     * @return the suitable color-background color or ambient light color for the points
//     */
//    @Override
//    public Color traceRay(Ray ray) {
//        //find intersaction points
//        List<Point3D> intersactions=_scene.geometries.findIntersections(ray);
//        if(intersactions!=null){
//            Point3D closest=ray.findClosestPoint(intersactions);
//            //color the point
//            calcColor(closest);
//        }
//        return _scene.background;
//    }
//
//    /**
//     *
//     * @param point the point
//     * @return the color of ambient light
//     */
//    private Color calcColor(Point3D point) {
//        return _scene.ambientLight.getIntensity();
//    }
//}
package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point3D> intersections = _scene.geometries.findIntersections(ray);
        if (intersections != null) {
            Point3D closestPoint = ray.findClosestPoint(intersections);
            return calcColor(closestPoint);
        }
        //ray did not intersect any geometrical object
        return _scene.background;
    }

    private Color calcColor(Point3D point) {
        return _scene.ambientLight.getIntensity();
    }
}
