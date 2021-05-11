package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * class scene
 * @author  Yael and Achinoam
 */
public class Scene {
    public String _name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight=new AmbientLight(Color.BLACK,0);
    public Geometries geometries=null;
    public List<LightSource> lights;/*new LinkedList<LightSource>()*/;

    /**
     *
     * @param name is the name of the scene
     */
    public Scene(String name) {
        _name = name;
        geometries=new Geometries();
        lights=lights=new LinkedList<LightSource>();

    }
    //chaining methods according to the instructions-LIKE BUILDER PATTERN

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

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
