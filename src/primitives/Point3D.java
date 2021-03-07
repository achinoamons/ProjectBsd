package primitives;

import java.util.Objects;

/**
 * basic point for RayTracing project
 *
 * @author achinoam&yael
 */
public class Point3D {
    final Coordinate _x;
    final  Coordinate _y;
    final Coordinate _z;
    final static Point3D ZERO = new Point3D(0d, 0d, 0d);

    /**
     * constructor for Point3D---צריך להמשיך תעוד
     *
     * @param x coordinate for x axis
     * @param y
     * @param z
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this(x.coord, y.coord, z.coord);
    }

    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }
//לשאול אם צריך get
//    public Coordinate getX() {
//        return _x;
//    }
//
//    public Coordinate getY() {
//        return _y;
//    }
//
//    public Coordinate getZ() {
//        return _z;
//    }

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
     * המרחק בריבוע
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
     * צריך תעוד
     */
    public double distance(Point3D other1) {
        return Math.sqrt(distanceSquared(other1));

    }

    public Vector subtract(Point3D pt2) {
        if (pt2.equals(this)) {
            throw new IllegalArgumentException("cannot create Vector to Point (0,0,0)");
        }
        return new Vector(new Point3D(
                pt2._x.coord - _x.coord,
                pt2._y.coord - _y.coord,
                pt2._z.coord - _z.coord
        ));
    }

    public Point3D add(Vector vector) {
        return new Point3D(_x.coord + vector._head._x.coord,
                _y.coord + vector._head._y.coord,
                _z.coord + vector._head._z.coord
        );
    }
}
