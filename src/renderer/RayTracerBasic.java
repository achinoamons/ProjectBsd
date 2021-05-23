package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1;
    //A CONSTRUCTOR that send to the father
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * @param ray get a ray and find intersactionspoints
     * @return the suitable color-background color or ambient light color for the points
     */
    @Override
    public Color traceRay(Ray ray) {
//        //find intersaction points
//        List<Point3D> intersactions=_scene.geometries.findIntersections(ray);
//        if(intersactions!=null){
//            Point3D closest=ray.findClosestPoint(intersactions);
//            //color the point
//           return calcColor(closest);
//        }
//        return _scene.background;

        //find intersaction points
        var intersections = _scene.geometries.findGeoIntersections(ray);
        if (intersections == null) {
            return _scene.background;
        }
        ;
        //color the point
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    /**
     * @param point the point
     * @return the color of ambient light
     */
    private Color calcColor(/*Point3D point*/ GeoPoint point, Ray ray) {
        // return _scene.ambientLight.getIntensity();
        return _scene.ambientLight.getIntensity()//ka*Ia
                .add(point.geometry.getEmission())//Ie
                .add(calcLocalEffects(point, ray));
        // add calculated light contribution from all light sources)

    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            return Color.BLACK;
        }
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;
        //for each light source calculate the diffuse and specular
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(lightSource,intersection)) {
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color
                        .add(calcDiffusive(kd, nl, lightIntensity), calcSpecular(nl,ks, l, n, v, nShininess, lightIntensity));
            }
            }
        }
        return color;
    }

    /**
     *
     * @param lightSource is the light
     * @param geoPoint is a point and its geometry
     * @return if the light is covered by another shape(=if there is shadow or not)
     */
    private boolean unshaded(LightSource lightSource, GeoPoint geoPoint) {
        Point3D point=geoPoint.point;
        Vector n=geoPoint.geometry.getNormal(point);
        Ray lightRay=new Ray(point,lightSource,n,DELTA);
        List<GeoPoint> intersections = _scene.geometries
                .findGeoIntersections(lightRay, lightSource.getDistance(point));
        return intersections ==null;


    }

    private Color calcSpecular(double nl,double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        //according to phong model formula
        //Calculating reflectance vector:
        Vector r=l.sutract(n.scale(nl*2));
        double minusvr=v.dotProduct(r)*-1;
        return lightIntensity.scale(ks*Math.pow(Math.max(0,minusvr),nShininess));
    }

    private Color calcDiffusive(double kd, double nl, Color lightIntensity) {
        //according to phong model formula
        double factor =kd*Math.abs(nl);
        return lightIntensity.scale(factor);
    }
}

