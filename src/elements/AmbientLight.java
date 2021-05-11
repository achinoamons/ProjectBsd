package elements;

import primitives.Color;

/**
 * An ambient light source represents a fixed-intensity and fixedcolor light source that
 * affects all objects in the scene equally.
 * @author Yael and Achinoam
 */
public class AmbientLight extends Light{
    /**
     * the intensity of the ambient light color
     */
   // final private Color _intensity;

    /**
     *
     * @param Ia THE INTENSITY COLOR
     * @param Ka the constant for the intensity
     */
    public AmbientLight(Color Ia,double Ka){
        super(Ia.scale(Ka));
    }

    /**
     * default constructor that send to the super(light) with color black
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * getter for intensity
     * @return the intensity color
     */
//    public Color getIntensity() {
//        return _intensity;
//    }
}
