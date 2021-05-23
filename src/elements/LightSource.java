package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface LightSource exercise a light source
 *
 * @author Yael and Achinoam
 *
 */
public interface LightSource {
    public Color getIntensity(Point3D p);
    public Vector getL(Point3D p);

    /**
     *
     * @param point
     * @return the distance between the light source and the point
     */
    double getDistance(Point3D point);
}
