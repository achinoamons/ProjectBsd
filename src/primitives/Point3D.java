package primitives;

import java.util.Objects;

import static primitives.Util.random;

/**
 * basic point for RayTracing project
 *
 * @author achinoam&yael
 */
public class Point3D {
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;
    public final static Point3D ZERO = new Point3D(0d, 0d, 0d);

    /**
     * constructor for Point3D
     *
     * @param x coordinate for x axis
     * @param y coordinate for y axis
     * @param z coordinate for z axis
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z)
    {
        this(x.coord, y.coord, z.coord);
    }

    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    public double getX() {
        return _x.coord;
    }

    public double getY() {
        return _y.coord;
    }

    public double getZ() {
        return _z.coord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) && _y.equals(point3D._y) && _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "(" + _x + "," + _y + "," + _z + ")";
    }

    /**
     * @param other for calculate the distance
     * @return The distance between two points squared
     */

    public double distanceSquared(Point3D other) {
        final double x1 = _x.coord;
        final double y1 = _y.coord;
        final double z1 = _z.coord;

        final double x2 = other._x.coord;
        final double y2 = other._y.coord;
        final double z2 = other._z.coord;

        return (((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) + ((z2 - z1) * (z2 - z1)));
    }

    /**
     * @param other1 for calculate the distance
     * @return Distance between 2 points
     */
    public double distance(Point3D other1) {
        return Math.sqrt(distanceSquared(other1));

    }

    /**
     * @param pt2 a second point
     * @return a vector from the second point to the point on which the operation is performed
     */
    public Vector subtract(Point3D pt2) {
        if (pt2.equals(this)) {
            throw new IllegalArgumentException("cannot create Vector to Point (0,0,0)");
        }
        return new Vector(new Point3D(
                _x.coord - pt2._x.coord,
                _y.coord - pt2._y.coord,
                _z.coord - pt2._z.coord
        ));
    }

    /**
     * @param vector for Adding a vector to a point
     * @return a new point
     */
    public Point3D add(Vector vector) {
        return new Point3D(_x.coord + vector._head._x.coord,
                _y.coord + vector._head._y.coord,
                _z.coord + vector._head._z.coord
        );
    }

    ////////////////////
    /**
     * find the minimum Coordinate
     *
     * @return if Coordinate x is minimum return 'x'<br>
     *         if Coordinate y is minimum return 'y'<br>
     *         if Coordinate z is minimum return 'z'<br>
     *         if all Coordinates are equal return 'x'
     */
    public char findMinimumCoordinate() {
        double x = getX(), y = getY(), z = getZ();
        double minimum = Math.abs(x);
        char index = 'x';
        y = Math.abs(y);
        if (y < minimum) {
            minimum = y;
            index = 'y';
        }
        z = Math.abs(z);
        if (z < minimum) {
            minimum = z;
            index = 'z';
        }
        return index;
    }

    /**
     * Function to the center of the circle,<br>
     * which receives a circle,<br>
     * and returns a random point on the circle
     *
     * @param dir    The normal exiting the circle,<br>
     *               together with the radius or height and length representing the
     *               circle,
     * @param radius radius of circle
     * @return Returns a random point on the circle
     */


    /**
     * Function to the center of the square,<br>
     * which receives a square,<br>
     * and returns a random point on the square
     *
     * @param dir    The normal exiting the square,<br>
     *               together with the radius or height and length representing the
     *               square,
     * @param width  of the square
     * @param height of the square
     * @return Returns a random point on the square
     */
    public Point3D randomPointOnSquare(Vector dir, double width, double height) {
        Vector firstNormal = dir.createOrthogonalVector();
        Vector secondNormal = firstNormal.crossProduct(dir).normalize();
        Point3D randomCirclePoint = this;
        double r;
        double wHalf = width / 2;
        r = random(0, wHalf);
        double x = random(-r, r);
        double hHalf = height / 2;
        r = random(0, hHalf);
        double y = random(-r, r);
        if (x != 0)
            randomCirclePoint = randomCirclePoint.add(firstNormal.scale(x));
        if (y != 0)
            randomCirclePoint = randomCirclePoint.add(secondNormal.scale(y));
        return randomCirclePoint;
    }
}
