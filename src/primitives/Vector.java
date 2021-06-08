package primitives;

import static primitives.Point3D.ZERO;

/**
 * class Vactor is the basic class representing a vector that start from the beginning.
 *
 * @author Achinoam and Yael
 */
public class Vector {
    /**
     * vector values
     */
    Point3D _head;

    /**
     * the mainly used constructor
     * Vector constructor receiving a Vector value by Point3D
     *
     * @param head value
     */
    public Vector(Point3D head) {
        if (head.equals(ZERO)) {
            throw new IllegalArgumentException("Vector head cannot be (0,0,0)");
        }
        _head = head;
    }

    /**
     * constructor of 3 doubles
     *
     * @param x for Coordinate value
     * @param y for Coordinate value
     * @param z for Coordinate value
     */

    public Vector(double x, double y, double z) {
        this (new Point3D(x,y,z));
        //       Coordinate xx = new Coordinate(x);
//        Coordinate yy = new Coordinate(y);
//        Coordinate zz = new Coordinate(z);//
//        _head._x = xx;
//        _head._x = yy;
//        _head._x = zz;
//
//        Point3D p = new Point3D(new Coordinate(x), new Coordinate(y), new Coordinate(z));
//        if (p.equals(ZERO)) {
//            throw new IllegalArgumentException("Vector head cannot be (0,0,0)");
//        }
//        _head = p;


    }

    /**
     * Vector constructor receiving a Vector values by Coordinate
     *
     * @param x Coordinate value
     * @param y Coordinate value
     * @param z Coordinate value
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        this(new Point3D(x,y,z));
//
//        Point3D p = new Point3D(x, y, z);
//        if (p.equals(ZERO)) {
//            throw new IllegalArgumentException("Vector head cannot be (0,0,0)");
//        }
//        _head = p;

    }


    /**
     * @param v the vector
     * @return the dot  product of the vector
     */
    public double dotProduct(Vector v) {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;

        double v1 = v._head._x.coord;
        double v2 = v._head._y.coord;
        double v3 = v._head._z.coord;
        return (u1 * v1 + u2 * v2 + u3 * v3);
    }

    /**
     * cross product
     *
     * @param v the second vector
     * @return Returns a new vector that is perpendicular to the existing two vectors
     */
    public Vector crossProduct(Vector v) {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;

        double v1 = v._head._x.coord;
        double v2 = v._head._y.coord;
        double v3 = v._head._z.coord;
        return new Vector(new Point3D(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        ));
    }

    /**
     * Vector addition
     *
     * @param v the vector
     * @return a new vector
     */
    public Vector add(Vector v) {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;

        double v1 = v._head._x.coord;
        double v2 = v._head._y.coord;
        double v3 = v._head._z.coord;
        return new Vector(new Point3D(
                u1 + v1,
                u2 + v2,
                u3 + v3
        ));
    }

    /**
     * Vector  subtraction
     *
     * @param v the vector
     * @return a new vector
     */
    public Vector sutract(Vector v) {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;

        double v1 = v._head._x.coord;
        double v2 = v._head._y.coord;
        double v3 = v._head._z.coord;
        return new Vector(new Point3D(
                u1 - v1,
                u2 - v2,
                u3 - v3
        ));
    }

    /**
     * Vector multiplication by a number - scalar
     *
     * @param factor the scale factor
     * @return a new vector
     */
    public Vector scale(double factor) {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;


        return new Vector(new Point3D(
                u1 * factor,
                u2 * factor,
                u3 * factor
        ));
    }

    /**
     * @return Calculate the length of the vector squared
     */
    public double lengthSquared() {
        double u1 = _head._x.coord;
        double u2 = _head._y.coord;
        double u3 = _head._z.coord;
        return u1 * u1 + u2 * u2 + u3 * u3;

    }

    /**
     * @return Calculate the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    /** normalizing the current vector
     * @return The vector normalization action that will change the vector itself
     */
    public Vector normalize() {
        //this.scale((1/(this.length())));
        //return this;
        _head = scale(1 / length())._head;
        return this;
    }

    /**
     * @return A normalization operation that returns a new normalized vector in the same direction as the original vector
     */
    public Vector normalized() {
        Vector v = new Vector(_head);
        v.normalize();
        return v;
    }

    @Override
    public String toString() {
        return "" + _head.toString();
    }

    public Point3D getHead() {
        return _head;
    }
    //////////////
    /**
     * This function return a Vertical Vector to "this" vector (this) most be
     * normalized!!!
     *
     * @return normalized normal vector
     */
    public Vector createOrthogonalVector() {
        double x = _head.getX(), y = _head.getY(), z = _head.getZ();
        switch (_head.findMinimumCoordinate()) {
            case 'x': {
                return new Vector(0, -z, y).normalize();
            }
            case 'y': {
                return new Vector(-z, 0, x).normalize();
            }
            case 'z': {
                return new Vector(y, -x, 0).normalize();
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + _head.findMinimumCoordinate());
        }
    }
}
