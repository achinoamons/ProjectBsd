package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class BasicRayTracer extends RayTracerBase {
    //consts for stop conditions in the recursion of refraction / reflections

    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;


    //A CONSTRUCTOR that send to the father
    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * @param ray get a ray and find intersactionspoints
     * @return the suitable color-background color or ambient light color for the points
     */
    @Override
    public Color traceRay(Ray ray) {


//        //find intersaction points
//        var intersections = _scene.geometries.findGeoIntersections(ray);
//        if (intersections == null) {
//            return _scene.background;
//        }
//
//        //color the point
//        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
//        // את התנאי הזה-יום ראשון אני הוספתי לעצמי ליתר בטחון
//        if (closestPoint==null){ return _scene.background;}
//        return calcColor(closestPoint, ray);
//
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? _scene.background : calcColor(closestPoint, ray);
    }

    /**
     * יום ראשון שמנו בהערה תרגיל 7
     * @param point the point
     * @return the color of ambient light
     */
//    private Color calcColor(/*Point3D point*/ GeoPoint point, Ray ray) {
//        // return _scene.ambientLight.getIntensity();
//        return _scene.ambientLight.getIntensity()//ka*Ia
//                .add(point.geometry.getEmission())//Ie
//                .add(calcLocalEffects(point, ray));
//        // add calculated light contribution from all light sources)
//
//    }

    /**
     * @param geoPoint the point
     * @return the color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = geoPoint.geometry.getEmission();
        color = color.add(calcLocalEffects(geoPoint, ray, k));
        //when level=1 we stop recursion
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray.getDir(), level, k));

    }


    private Color calcGlobalEffects(GeoPoint geoPoint, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Material material = geoPoint.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            //send to calc global effect-Mutual recursion(because it will send to calc color)
            //sending reflectance rays and Transparency rays
            color = calcGlobalEffect(constructReflectedRay(geoPoint.point, v, n), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(geoPoint.point, v, n), level, material.kT, kkt));
        return color;
    }

    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        //return a new ray with the same direction
        return new Ray(point, v, n);
    }

    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        //calculate r according to the formula
        Vector r = v.sutract(n.scale(2 * v.dotProduct(n)));
        return new Ray(point, r, n);
    }

    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }


    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
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
                //adding shadow or not according to the answer from transparency function
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color
                            .add(calcDiffusive(kd, nl, lightIntensity), calcSpecular(nl, ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * @param lightSource is the light
     * @param geoPoint    is an intersection point and its geometry
     * @return double 0 or 1 that show if the light is covered by another shape(=if there is shadow or not)
     */

    private double transparency(LightSource lightSource, Vector l, Vector n, GeoPoint geoPoint) {
        Point3D point = geoPoint.point;
        //the opposite direction of the light source vector l
        Vector v = lightSource.getL(point).scale(-1);
        Ray lightRay = new Ray(point, v, n);
        //
//        List<GeoPoint> intersections = _scene.geometries
//                .findGeoIntersections(lightRay, lightSource.getDistance(point));
//        return intersections ==null;

        //Only objects without a transparency coefficient (transparency coefficient = 0) will cause shading
        double lightDistance = lightSource.getDistance(geoPoint.point);
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().kT;
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }

        }
        return ktr;


    }
//    private boolean unshaded(LightSource lightSource, GeoPoint geoPoint) {
//        Point3D point=geoPoint.point;
//        Vector n=geoPoint.geometry.getNormal(point);
//        Vector v=lightSource.getL(point).scale(-1);
//        Ray lightRay=new Ray(point,v,n);
//        //מחקתי של 3 אלו בשלב 2 אחרי שלב 1
////        List<GeoPoint> intersections = _scene.geometries
////                .findGeoIntersections(lightRay, lightSource.getDistance(point));
////        return intersections ==null;
//        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay);
//        if (intersections == null) return true;
//        double lightDistance = lightSource.getDistance(geoPoint.point);
//        for (GeoPoint gp : intersections) {
//            if (alignZero(gp.point.distance(geoPoint.point) -lightDistance) <= 0 &&
//                    gp.geometry.getMaterial().kT == 0)
//                return false;
//        }
//        return true;
//
//
//    }

    private Color calcSpecular(double nl, double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        //according to phong model formula
        //Calculating reflectance vector:
        Vector r = l.sutract(n.scale(nl * 2));
        double minusvr = v.dotProduct(r) * -1;
        return lightIntensity.scale(ks * Math.pow(Math.max(0, minusvr), nShininess));
    }

    private Color calcDiffusive(double kd, double nl, Color lightIntensity) {
        //according to phong model formula
        double factor = kd * Math.abs(nl);
        return lightIntensity.scale(factor);
    }

    /**
     *
     * @param ray for finding intersection
     * @return :the closest intersection point
     * An action that will calculate the intersection and also  the intersection closest to the beginning of the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var intersactions = _scene.geometries.findGeoIntersections(ray);
        //if there is no intersection
        if (intersactions == null) {
            return null;

        }
        GeoPoint closestintersaction = ray.findClosestGeoPoint(intersactions);
        return closestintersaction;
    }
}

