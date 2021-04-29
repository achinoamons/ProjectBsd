package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * class scene
 * @author  Yael and Achinoam
 */
public class Scene {
    public String _name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight=new AmbientLight(Color.BLACK,0);
    public Geometries geometries=null;

    /**
     *
     * @param name is the name of the scene
     */
    public Scene(String name) {
        _name = name;
        geometries=new Geometries();

    }
    //chaining methods according to the instructions-LIKE BUILDER PATTERN
    public Scene setBackground(Color background) {
        this.background = background;
        return  this;
    }

    public Scene setAmbientLight(AmbientLight ambientlight) {
        this.ambientLight = ambientlight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return  this;
    }

}
