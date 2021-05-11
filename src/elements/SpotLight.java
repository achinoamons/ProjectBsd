package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * class SpotLight to apply spot lighting
 * Models point light source with direction (such as a luxo lamp)
 *
 * @author Yael and Achinoam
 */
public class SpotLight extends PointLight {
    private Vector _direction;

    /**
     * @param intensity---like super
     * @param position---like  super


     * @param direction-for    direction
     */
    public SpotLight(Color intensity, Point3D position/*, double kC, double kL, double kQ,*/, Vector direction) {
        super(intensity, position/*, kC, kL, kQ*/);
        _direction = direction.normalized();
    }


    @Override
    public Color getIntensity(Point3D p) {
        //dir dot product with L
        double projection = _direction.dotProduct(getL(p));
//        //if they are orthogonal-the color is black because there is no spot light
//        if (Util.isZero(projection)) {
//            return Color.BLACK;
//        }
        double factor = alignZero(Math.max(0, projection));
        Color pointlightIntensity = super.getIntensity(p);
        //אולי צריך לזרוק חריגה במקרה שמאונכים-זוית 0

        return (pointlightIntensity.scale(factor));
    }

}



