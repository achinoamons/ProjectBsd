package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class PointLight to apply point lighting
 *Models omni-directional point source (such as a bulb)
 * @author Yael and Achinoam
 */
public class PointLight extends Light implements LightSource {

    private Point3D _position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * @param intensity IS THE COLOR FOR INTENSITY
     * @param position  position

     */
    public PointLight(Color intensity, Point3D position/*, double kC, double kL, double kQ*/) {
        super(intensity);
        _position = position;
//        this.kC = kC;
//        this.kL = kL;
//        this.kQ = kQ;
    }

    /**
     * setters for this fields
     *
     * @param kC,kL,Kq-for attenuation with distance (d)
     * @return the object itself-chaining method (like builder pattern)
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     *
     * @param point is the first point
     * @return the distance between 2 points-helping func
     */
    @Override
    public double getDistance(Point3D point)
    {
        return _position.distance(point);
    }
    @Override
    public Color getIntensity(Point3D p) {
        //double dsquared = p.distanceSquared(_position);
        //double d = p.distance(_position);
        double d = getDistance(p);
        double dsquared = d*d;

        return (this.getIntensity().scale(1/(kC + kL * d + kQ * dsquared)));
    }

    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        }
        return p.subtract(_position).normalize();
    }
}
