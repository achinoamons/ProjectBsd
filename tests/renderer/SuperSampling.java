package renderer;
import elements.AmbientLight;
import elements.Camera;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

public class SuperSampling {

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void AntiAliasing() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setDistance(50) //
                .setViewPlaneSize(80, 80)
                .setSizeGrid(81)
                .setAntialiacing(true);
        Scene scene = new Scene("Anti Aliasing test")//
                .setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
                .setBackground(new Color(75, 127, 90));

        scene.geometries.add(new Sphere(new Point3D(0, 0, -100), 50)

        ); // down
        // right
        int p = 500;
        ImageWriter imageWriter = new ImageWriter("base whit Anti Aliasing", p, p);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
                //

               //


        render.renderImage();
        render.writeToImage();

        camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setDistance(50) //
                .setViewPlaneSize(80, 80);



        ImageWriter imageWriter1 = new ImageWriter("base whitout Anti Aliasing", p, p);
        Render render1 = new Render() //
                .setImageWriter(imageWriter1) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));//


        render1.renderImage();
        render1.writeToImage();
    }
}
