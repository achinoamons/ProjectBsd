package renderer;


import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

/**
 * Testing basic shadows
 *
 * @author Dan
 */
public class ShadowTests {
    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(200, 200).setDistance(1000).setSizeGrid(81);

    /**
     * Produce a picture of a sphere and triangle with point light and shade
     */
    @Test
    public void sphereTriangleInitial() {
        scene.geometries.add( //
                new Sphere( new Point3D(0, 0, -200),60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setkL(1E-5).setkQ(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene))
               ;
        render.renderImage();
        render.writeToImage();
    }
    //////////////////////////////////
    //moving the triangle only-a litlle bit
    @Test
    public void sphereTriangleInitialMovet1() {
        scene.geometries.add( //
                new Sphere( new Point3D(0, 0, -200),60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)), //
                new Triangle(new Point3D(-65, -35, 0), new Point3D(-35, -65, 0), new Point3D(-63, -63, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setkL(1E-5).setkQ(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitialMovet1", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }


    //////////////////////////////////
    //moving the triangle only-a litlle bit moreeee
    @Test
    public void sphereTriangleInitialMovet2() {
        scene.geometries.add( //
                new Sphere( new Point3D(0, 0, -200),60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)), //
                new Triangle(new Point3D(-48, -18, 0), new Point3D(-18, -48, 0), new Point3D(-46, -46, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setkL(1E-5).setkQ(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitialMovet2", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
    ////////////////////////////////////////////
    @Test
    public void sphereTriangleInitialMovel3() {
        scene.geometries.add( //
                new Sphere( new Point3D(0, 0, -200),60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-90, -90, 190), new Vector(1, 1, -3)) //
                        .setkL(1E-5).setkQ(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitialMovel3", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }
///////////////////////////////////////
////////////////////////////////////////////
@Test
public void sphereTriangleInitialMovel4() {
    scene.geometries.add( //
            new Sphere( new Point3D(0, 0, -200),60) //
                    .setEmission(new Color(java.awt.Color.BLUE)) //
                    .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)), //
            new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                    .setEmission(new Color(java.awt.Color.BLUE)) //
                    .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)) //
    );
    scene.lights.add( //
            new SpotLight(new Color(400, 240, 0), new Point3D(-70, -70, 50), new Vector(1, 1, -3)) //
                    .setkL(1E-5).setkQ(1.5E-7));

    Render render = new Render(). //
            setImageWriter(new ImageWriter("shadowSphereTriangleInitialMovel4", 400, 400)) //
            .setCamera(camera) //
            .setRayTracer(new BasicRayTracer(scene));
    render.renderImage();
    render.writeToImage();
}
    /**
     * Produce a picture of a two triangles lighted by a spot light with a Sphere
     * producing a shading
     */
    @Test
    public void trianglesSphere() {
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Sphere( new Point3D(0, 0, -115),30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point3D(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setkL(4E-4).setkQ(2E-5));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("shadowTrianglesSphere", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

}

