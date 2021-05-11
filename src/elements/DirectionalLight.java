package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * class DirectionalLight to apply directional lighting
 *Light source is far away  - like Sun
 * @author Yael and Achinoam
 *
 */
public class DirectionalLight extends Light implements LightSource{

    private Vector _direction;

    /**
     * constructor that initiate the fields
     * @param intensity is the color intensity
     * @param direction is vector of direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
     //No attenuation with distance
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {

        return _direction;
    }
}
