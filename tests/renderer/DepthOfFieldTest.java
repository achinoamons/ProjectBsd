package renderer;
import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import java.util.List;

public class DepthOfFieldTest {
    @Test
    public void DepthOfField() {

        Scene scene = new Scene("Depth Of Field");

        Camera camera = (new Camera(new Point3D(150, 100, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)))
                .setDistance(1400).setViewPlaneSize(700, 700)
                 .setDepthOfField(true);

        scene.setBackground(new Color(25, 25, 112));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.geometries.add(
                new Sphere(Point3D.ZERO, 50).setEmission(new Color(java.awt.Color.gray))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Sphere(new Point3D(100, 50, -100), 50).setEmission(new Color(java.awt.Color.RED))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Sphere(new Point3D(200, 100, -200), 50).setEmission(new Color(java.awt.Color.GREEN))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Sphere(new Point3D(300, 150, -300), 50).setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Sphere(new Point3D(400, 200, -400), 50).setEmission(new Color(java.awt.Color.darkGray))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100))

        );

        scene.lights
                .addAll(List.of(
                        new SpotLight(new Color(1000, 600, 1000), new Point3D(0, 100, 100), Point3D.ZERO.subtract(new Point3D(0, 100, 100))).setkC(1)
                                .setkL(0.0001).setkQ(0.00005),
                        new DirectionalLight(new Color(255, 215, 255), new Vector(0, -0.2, -1))));
        int p = 1000;
        ImageWriter imageWriter = new ImageWriter("Depth Of Field", p, p);
        Render render = new Render().setImageWriter(imageWriter)
                .setCamera(camera
                        .setDistanceToFocalPlane(new Point3D(150, 100, 1000).distance(new Point3D(200, 100, -200)))
                        .setSizeForApertureWindow(100).setNumOfRayFormApertureWindowToFocalPoint(50))
                .setRayTracer(new BasicRayTracer(scene));

        //

        render.renderImage();
        render.writeToImage();

        ///////////////without
        ImageWriter imageWriter1 = new ImageWriter("Depth Of Fieldwithout", p, p);
        Render render1 = new Render().setImageWriter(imageWriter1)
                .setCamera(camera
                        .setDistanceToFocalPlane(new Point3D(150, 100, 1000).distance(new Point3D(200, 100, -200)))
                        .setSizeForApertureWindow(100).setNumOfRayFormApertureWindowToFocalPoint(50).setDepthOfField(false))
                .setRayTracer(new BasicRayTracer(scene));

        //

        render1.renderImage();
        render1.writeToImage();


    }
}
