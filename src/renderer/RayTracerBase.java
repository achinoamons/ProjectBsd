//package renderer;
//
//import primitives.Color;
//import primitives.Ray;
//import scene.Scene;
//
//public abstract class RayTracerBase {
//    protected Scene _scene;
//
//    public RayTracerBase(Scene scene) {
//        _scene = scene;
//    }
//
//    /**
//     *the abstract func get a ray and return color
//     * @param ray get a ray
//     * @return color
//     */
//     public abstract Color traceRay(Ray ray);
//
//
//}
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {

    protected Scene _scene;

    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    public abstract Color traceRay(Ray ray);

}
