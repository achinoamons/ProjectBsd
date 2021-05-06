package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * @author Yael and Achinoam
 * abstract class that helps for rndering
 */
public abstract class RayTracerBase {
    protected Scene _scene;

    /**
     *
     * @param scene for initiate the field _scene
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     *the abstract func get a ray and return color
     * @param ray get a ray
     * @return color
     */
     public abstract Color traceRay(Ray ray);


}

