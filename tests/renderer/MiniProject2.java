package renderer;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;


public class MiniProject2 {
    @Test
    public void test()
    {
        Scene scene = new Scene("test");
        //Camera camera=(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setDistance(1000);
        camera.setViewPlaneSize(200,250);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setBackground(Color.BLACK);

        //Plane plane1 = new Plane(new Point3D(0, 5, 100), new Vector(0, 1, 0), new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0.8, 0));
        Plane plane2 = new Plane(new Point3D(0, 0, -300), new Vector(0, 0, 1));//setEmission(new Color(0, 40, 60)).setMaterial(new Material() .setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0).setkR(0));

//        for(int i=108, count=0; i>-100; i -=18, count++)
//        {
//            if(count%3 == 0)
//                scene.geometries.add(new Triangle(new Point3D(i-18, -140, -50), new Point3D(i, -140, -50), new Point3D(i-9, -110,-50)).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0.4).setkR(0.4)));
//            if(count%3 == 1)
//                scene.geometries.add(new Triangle(new Point3D(i-18, -140, -50), new Point3D(i, -140, -50), new Point3D(i-9, -110,-50)).setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0).setkR(0.4)));
//            if(count%3 == 2)
//                scene.geometries.add(new Triangle(new Point3D(i-18, -140, -50), new Point3D(i, -140, -50), new Point3D(i-9, -110,-50)).setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0.4).setkR(0.4) ));
//        }
        for(int i=108, count=0; i>-100; i -=18, count++)
        {
            if(count%3 == 0)
                scene.geometries.add(new Triangle(new Point3D(i-18, 140, -50), new Point3D(i, 140, -50), new Point3D(i-9, 110,-50)).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0.4).setkR(0.4)));
            if(count%3 == 1)
                scene.geometries.add(new Triangle(new Point3D(i-18, 140, -50), new Point3D(i, 140, -50), new Point3D(i-9, 110,-50)).setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0).setkR(0.4)));
            if(count%3 == 2)
                scene.geometries.add(new Triangle(new Point3D(i-18, 140, -50), new Point3D(i, 140, -50), new Point3D(i-9, 110,-50)).setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0.4).setkR(0.4) ));
        }
        double i=15, j=3;
        for(int x=-90, y=140; x<0; x+=j, y-=i, i+=1.5, j+=0.2)
        {
            scene.geometries.add(new Sphere(new Point3D(x, y, 150), 10).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.8).setkR(0) ));
            scene.lights.add(new SpotLight(new Color(400, 300, 300),new Point3D(x, y, 150),camera.getvTO()).setkC(1).setkL(0.005).setkQ(0.0005));
        }
        i=15;
        j=-3;
        for(int x=90, y=140; x>0; x+=j, y-=i, i+=1.5, j-=0.2)
        {
           // scene.geometries.add(new Sphere(new Point3D(x, y, 150), 10).setEmission(new Color(40, 0, 20)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0).setkR(0.4)));
            scene.geometries.add(new Sphere(new Point3D(x, y, 150), 10).setEmission(new Color(java.awt.Color.YELLOW)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.8).setkR(0) ));
            scene.lights.add(new SpotLight(new Color(400, 300, 300),new Point3D(x, y, 150),camera.getvTO()).setkC(1).setkL(0.005).setkQ(0.0005));

        }
        //DUBI
        scene.geometries.add(

                new Sphere(new Point3D(4, 8 ,18),25).setEmission(new Color(77,38,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),

                new Sphere(new Point3D(26, -11, 18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),

                new Sphere(new Point3D(-14,-12, 18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),

                new Sphere(new Point3D(-21,16,18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),

                new Sphere(new Point3D(27,16,18),8).setEmission(new Color(77,38,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
                new Sphere(new Point3D(3,39,18),18).setEmission(new Color(77,38,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
                new Sphere(new Point3D(11,43,35.71),2.2).setEmission(new Color(java.awt.Color.BLACK)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0).setkR(0)),
                new Sphere(new Point3D(-4,43,35.94),2.2).setEmission(new Color(java.awt.Color.BLACK)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0).setkR(0)),
                new Sphere(new Point3D(11,43,35.71),1.1).setEmission(new Color(java.awt.Color.BLACK)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0).setkR(0)),
                new Sphere(new Point3D(-4,43,35.94),1.1).setEmission(new Color(java.awt.Color.BLACK)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0).setkR(0)),
               //nose
                //new Triangle(new Point3D(0.2,36.6,37.6),new Point3D(5.7,36.85,37.70),new Point3D(3.33,31.8,36.65)).setEmission(new Color(java.awt.Color.gray)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0).setkR(0)),
                new Sphere(new Point3D(2.699,37.57,37.94),2.5).setEmission(new Color(java.awt.Color.gray)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0).setkR(0)),

                new Sphere(new Point3D(-8,58,18),7).setEmission(new Color(77,38,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
                new Sphere(new Point3D(14,58,18),7).setEmission(new Color(77,38,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),

            //ice cream
                new Triangle(new Point3D(-35,25,18),new Point3D(-28,19,19),new Point3D(-27,0,0)).setEmission(new Color(230,153,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),

                new Triangle(new Point3D(-23,26,0),new Point3D(-28,19,19),new Point3D(-27,0,0)).setEmission(new Color(230,153,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
                new Triangle(new Point3D(-35,25,18),new Point3D(-23,26,0),new Point3D(-27,0,0)).setEmission(new Color(230,153,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
//                new Triangle(new Point3D(-38,25,20),new Point3D(-26,25,25),new Point3D(-32,9,19)).setEmission(new Color(230,153,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
//
//                new Triangle(new Point3D(-27,25,12),new Point3D(-38,25,20),new Point3D(-32,9,19)).setEmission(new Color(230,153,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
//                new Triangle(new Point3D(-26,25,25),new Point3D(-38,25,20),new Point3D(-32,9,19)).setEmission(new Color(230,153,0)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
                new Sphere(new Point3D(-28,28,19),8).setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0))

        );

        scene.geometries.add(plane2.setEmission(new Color(255,153,153)).setMaterial(new Material() .setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0).setkR(0)));
        /////////////mirror
        scene.geometries.add(new Plane(new Point3D(0, -20, 0), new Vector(0, -40, 0)).setEmission(new Color(0,40,60)).setMaterial(new Material() .setkR(1)));

//        /////////////mirror

//                scene.geometries.add(new Triangle(new Point3D(-140,-30,-70), new Point3D(-140,-140,0),
//                new Point3D(156,-30, -70)) //
//                .setEmission(new Color(255,102,102)) //
//                .setMaterial(new Material().setkR(1)));
//
//
//        scene.geometries.add(new Triangle(new Point3D(-140,-140,0), new Point3D(156,-30,-70),
//                new Point3D(156,-140, 0)) //
//                .setEmission(new Color(204,0,82)) //
//                .setMaterial(new Material().setkR(1)));



        /////////////////////////

        DirectionalLight direction_light = new DirectionalLight(new Color(0, 0, 1), new Vector(1, -1, 1));
        SpotLight spot_light = new SpotLight(new Color(0, 1, 0), new Point3D(4,8,18),  new Vector(0,0, 1));//1, 4E-4, 2E-5,;
        PointLight point_light = new PointLight(new Color(400, 300, 300), new Point3D(0,145,50));
        //on mirror
        PointLight point_light2 = new PointLight(new Color(400, 300, 300), new Point3D(-74,-20,58));

        ImageWriter imageWriter = new ImageWriter("miniPfoject2 test",  600, 500);

        scene.lights.add(point_light.setkC(1).setkL(0.05).setkQ(0.00005));
        //scene.lights.add(point_light2.setkC(1).setkL(0.005).setkQ(0.00005));
        //.setkL(0.0005).setkQ(0.000005)
        scene.lights.add(direction_light);
        scene.lights.add(spot_light.setkC(1).setkL(4E-4).setkQ(2E-5));


        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));


        render.renderImage();
        render.writeToImage();
    }
}


//////////////////////////////
//Scene scene = new Scene("test");
//    Camera camera=(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
//        camera.setDistance(1000);
//                camera.setViewPlaneSize(200,250);
//                //scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
//                //scene.setBackground(Color.BLACK);
//
//                //Plane plane1 = new Plane(new Point3D(0, 5, 100), new Vector(0, 1, 0), new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0.8, 0));
//                Plane plane2 = new Plane(new Point3D(0, 0, 300), new Vector(0, 0, 1));//setEmission(new Color(0, 40, 60)).setMaterial(new Material() .setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0).setkR(0));
//
//                for(int i=108, count=0; i>-100; i -=18, count++)
//                {
//                if(count%3 == 0)
//                scene.geometries.add(new Triangle(new Point3D(i-18, -140, 50), new Point3D(i, -140, 50), new Point3D(i-9, -110,50)).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0.4).setkR(0.4)));
//                if(count%3 == 1)
//                scene.geometries.add(new Triangle(new Point3D(i-18, -140, 50), new Point3D(i, -140, 50), new Point3D(i-9, -110,50)).setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0).setkR(0.4)));
//                if(count%3 == 2)
//                scene.geometries.add(new Triangle(new Point3D(i-18, -140, 50), new Point3D(i, -140, 50), new Point3D(i-9, -110,50)).setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0.4).setkR(0.4) ));
//                }
//
//                double i=15, j=3;
//                for(int x=-90, y=140; x<0; x+=j, y-=i, i+=1.5, j+=0.2)
//        {
//        scene.geometries.add(new Sphere(new Point3D(x, y, 200), 10).setEmission(new Color(40, 0, 20)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0).setkR(0.4) ));
//        }
//        i=15;
//        j=-3;
//        for(int x=90, y=140; x>0; x+=j, y-=i, i+=1.5, j-=0.2)
//        {
//        scene.geometries.add(new Sphere(new Point3D(x, y, 20), 10).setEmission(new Color(40, 0, 20)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0).setkR(0.4)));
//        }
//        scene.geometries.add(
//
//        new Sphere(new Point3D(5, 10 ,20),15).setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
//
//        new Sphere(new Point3D(35, 45, 70),15).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
//
//        new Sphere(new Point3D(10, 70, 150),15).setEmission(new Color(java.awt.Color.ORANGE)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
//
//        new Sphere(new Point3D(-20, -25,70),15).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0)),
//
//        new Sphere(new Point3D(-10, -70, 150),15).setEmission(new Color(java.awt.Color.ORANGE)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0.5).setkR(0))
//
//        );
//
//        scene.geometries.add(plane2.setEmission(new Color(0, 40, 60)).setMaterial(new Material() .setkD(0.5).setkS(0.5).setnShininess(1200).setkT(0).setkR(0)));
//
//        DirectionalLight direction_light = new DirectionalLight(new Color(0, 0, 1), new Vector(1, -1, 1));
//        SpotLight spot_light = new SpotLight(new Color(0, 1, 0), new Point3D(-100, 0, 0),  new Vector(0,0, 1));//1, 4E-4, 2E-5,;
//        PointLight point_light = new PointLight(new Color(400, 300, 300), new Point3D(0, 145, 50));
//
//        ImageWriter imageWriter = new ImageWriter("miniPfoject2 test",  600, 500);
//
//        scene.lights.add(point_light.setkC(1).setkL(0.0005).setkQ(0.000005));
//        scene.lights.add(direction_light);
//        scene.lights.add(spot_light.setkC(1).setkL(4E-4).setkQ(2E-5));
//
//
//        Render render = new Render() //
//        .setImageWriter(imageWriter) //
//        .setCamera(camera) //
//        .setRayTracer(new BasicRayTracer(scene));
//
//
//        render.renderImage();
//        render.writeToImage();
