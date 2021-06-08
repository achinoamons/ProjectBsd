package renderer;

import elements.*;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import java.util.List;

public class MiniProject1 {
    @Test
    public void MiniProject1Test() {

        Scene scene = new Scene("Depth Of Field");

        Camera camera = (new Camera(new Point3D(150, 100, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)))
                .setDistance(1400).setViewPlaneSize(700, 700).setSizeGrid(100);

        scene.setBackground(new Color(255,153,153));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.geometries.add(
                new Sphere(Point3D.ZERO, 50).setEmission(new Color(204,0,102))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Sphere(new Point3D(100, 50, -100), 50).setEmission(new Color(java.awt.Color.RED))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Sphere(new Point3D(200, 100, -200), 50).setEmission(new Color(102,0,51))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Sphere(new Point3D(300, 150, -300), 50).setEmission(new Color(102,0,102))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Sphere(new Point3D(400, 200, -400), 50).setEmission(new Color(196,47,181))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Triangle(new Point3D(39.83, -30.22, 0),new Point3D(-41.16,-28.39,0), new Point3D(0, -100, 0)).setEmission(new Color(150,75,0))/*.setMaterial(new Material().setkT(1)*/ ,
                new Triangle(new Point3D(57.74, 23.62, -100),new Point3D(142.9, 24.35, -100), new Point3D(100, -50, -100)).setEmission(new Color(150,75,0)),
               new Triangle(new Point3D(164.58, 66.71, -200),new Point3D(236.17, 68.15, -200), new Point3D(200, 0, -200)).setEmission(new Color(150,75,0)),
                new Triangle(new Point3D(258.17, 127.42, -300),new Point3D(342.74, 126.12, -300), new Point3D(300, 50, -300)).setEmission(new Color(150,75,0)),
                new Triangle(new Point3D(358.97, 174.04, -400),new Point3D(440.5, 173.61, -400), new Point3D(400, 100, -400)).setEmission(new Color(150,75,0)),
                /////////////
                new Triangle(new Point3D(465,-415,1200), new Point3D(-600,-400,-600),
                        new Point3D(800,400,-600)) //) // // //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setkR(1))
//                new Plane(new Point3D(80,90,-900),new Vector(0,0,1))
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkR(1))


//                new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
//                        new Point3D(-1500, -1500, -2000)) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkR(0.5))

        );

        scene.lights
                .addAll(List.of(
                        new SpotLight(new Color(1000, 600, 1000), new Point3D(0, 100, 100), Point3D.ZERO.subtract(new Point3D(0, 100, 100))).setkC(1)
                                .setkL(0.0001).setkQ(0.00005),
                        new DirectionalLight(new Color(255, 215, 255), new Vector(0, -0.2, -1)),
                new PointLight(new Color(500, 300, 0), new Point3D(159,240,0))));
        int p = 1000;
        ImageWriter imageWriter = new ImageWriter("mini1", p, p);
        Render render = new Render().setImageWriter(imageWriter)
                .setCamera(camera
                        .setDistanceToFocalPlane(new Point3D(150, 100, 1000).distance(new Point3D(200, 100, -200)))
                        .setSizeForApertureWindow(100).setNumOfRayFormApertureWindowToFocalPoint(50))
                .setRayTracer(new BasicRayTracer(scene));
               //.setDepthOfField(true);
               // . setAntialiacing(true);
        //

        render.renderImage();
        render.writeToImage();

    }
}





































//package renderer;
//import elements.*;
//import geometries.Geometries;
//import geometries.Plane;
//import geometries.Sphere;
//import geometries.Triangle;
//import org.junit.jupiter.api.Test;
//import primitives.Color;
//import primitives.Material;
//import primitives.Point3D;
//import primitives.Vector;
//import scene.Scene;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MiniProject1 {
//    @Test
//    public void MiniProject1Test() {
//        Scene scene = new Scene("MiniProject1Test");
//        Camera camera=(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
//
//        camera.setDistance(1000);
//        camera.setViewPlaneSize(2000, 2000);
//        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
//        scene.setBackground(Color.BLACK);
//       /////////////////
//        Sphere sphere = new Sphere(new Point3D(0, -50, 80),20);
//        Sphere sphere2 = new Sphere(new Point3D(0, -10, 80),9);
//        Sphere sphere3 = new Sphere(new Point3D(0, 10, 80),10);
//        Sphere sphere4 = new Sphere(new Point3D(0, 30, 80),5);
//        Sphere sphere5 = new Sphere(new Point3D(0, -100, 80),40);
//
//
//        ////////////////
//
//       Geometries geo1=new Geometries();
//        Geometries geo2=new Geometries();
//        Geometries geo3=new Geometries();
//        geo3.add(sphere3,sphere4);
//        geo2.add(geo3,sphere5);
//       geo1.add(sphere,sphere2,geo2);
//        scene.geometries.add( //
//                new Sphere( new Point3D(0,-100,80),40) //
//                        .setEmission(new Color(20,20,20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(50,-280,20),40) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(50,-120,80),60) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(50,0,-10),80) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(50,150,80),100) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(70,-280,80),80) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(70,-120,80),60) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(70,0,80),40) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(70,50,80),30) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(70,70,80),20) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(70,90,80),10) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(70,110,20),1) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(-100,-50,80),20) //
//                        .setEmission(new Color(0, 0, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0.5).setkR(0.2)),
//                new Sphere( new Point3D(-100,-10,80),9) //
//                        .setEmission(new Color(0, 20, 0)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(-100,10,80),10) //
//                        .setEmission(new Color(20, 20, 0)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(-100,30,80),5) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(-100,-100,80),40) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(100,-50,80),20) //
//                        .setEmission(new Color(0, 0, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0.5).setkR(0.2)),
//                new Sphere( new Point3D(100,-10,30),9) //
//                        .setEmission(new Color(0, 20, 0)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(100,10,30),10) //
//                        .setEmission(new Color(20, 20, 0)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(100,30,30),5) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2)),
//                new Sphere( new Point3D(100,-100,30),40) //
//                        .setEmission(new Color(20, 20, 20)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300).setkT(0).setkR(0.2))
//
//              ///
//
//                );
//
//        Point3D pos = new Point3D(20, -50, 0);
//        Color color = new Color(400, 300, 300);
//
//        PointLight point_light = new PointLight(color, pos).setkC(1).setkL(0.0005).setkQ(0.000005);
//        DirectionalLight directional_light = new DirectionalLight(color ,  new Vector(1,1,1));
//
//        ImageWriter imageWriter = new ImageWriter("MIni project 1 Test", 1000, 1000);
//        List<LightSource> lights = new ArrayList<LightSource>();
//        lights.add(point_light);
//        lights.add(directional_light);
//        scene.setLights(lights);
//       // Render render = new Render(imageWriter, scene);
//        Render render = new Render() //
//                .setImageWriter(imageWriter) //
//                .setCamera(camera) //
//                .setRayTracer(new BasicRayTracer(scene));
//        //render.setSampleCount(80);
//        render.renderImage();
//        render.writeToImage();
//
//    }
//}
