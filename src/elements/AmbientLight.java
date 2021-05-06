package elements;

import primitives.Color;

/**
 * An ambient light source represents a fixed-intensity and fixedcolor light source that
 * affects all objects in the scene equally.
 * @author Yael and Achinoam
 */
public class AmbientLight {
    /**
     * the intensity of the ambient light color
     */
    final private Color _intensity;

    /**
     *
     * @param Ia THE INTENSITY COLOR
     * @param Ka the constant for the intensity
     */
    public AmbientLight(Color Ia,double Ka){
        _intensity=Ia.scale(Ka);
    }

    /**
     * getter for intensity
     * @return the intensity color
     */
    public Color getIntensity() {
        return _intensity;
    }
}