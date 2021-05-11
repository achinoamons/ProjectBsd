package elements;

import primitives.Color;

/**
 * abstract class Light base class to express the lighting of the scene
 *
 * @author Yael and Achinoam
 *
 */
abstract class Light {
    /**
     * Light value
     */
    private Color _intensity;
    /**
     * Light constructor
     *
     * @param intensity
     */
    protected Light(Color intensity) {
        _intensity = intensity;
    }

    /**
     * The get function to get the color
     *
     * @return color-the intensity
     */
    public Color getIntensity() {
        return _intensity;
    }
}
