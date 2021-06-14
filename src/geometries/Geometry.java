package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry extends Intersectable {
    public abstract Vector getNormal(Point3D point);

    //a field for color for each geometry
    protected Color emission = Color.BLACK;

    /**
     *
     * @return the color of the shape
     */
    public Color getEmission() {
        return emission;
    }

    //a field for material for each geometry
    private Material material = new Material();

    /**
     * getter
     *
     * @return the field material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setters for this field MATERIAL
     *
     * @return the object itself-chaining method (like builder pattern)
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * @param emission is the color for each geometry
     * @return the object itself-chaining method
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}
