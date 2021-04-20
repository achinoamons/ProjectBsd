package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    private Point3D _p0;
    private Vector _vRIGHT;
    private Vector _vUP;
    private Vector _vTO;
    private double _width;
    private double _height;
    private double _distance;

    public Point3D getP0() {
        return _p0;
    }

    public Vector getvRIGHT() {
        return _vRIGHT;
    }

    public Vector getvUP() {
        return _vUP;
    }

    public Vector getvTO() {
        return _vTO;
    }

    public Camera(Point3D p0, Vector vUP, Vector vTO) {
        _p0 = p0;
        if(!isZero(vTO.dotProduct(vUP))){
            throw new IllegalArgumentException("vup is not orthogonal to vto");
        }
        _vUP = vUP.normalized();
        _vTO = vTO.normalized();
        _vRIGHT=_vTO.crossProduct(_vUP);

    }
    public Camera setViewPlaneSize(double width, double height){
        _height=height;
        _width=width;
        return this;
    }
    public Camera setDistance(double distance){
        _distance=distance;
        return this;
    }
    /**
     *
     * @param nX
     * @param nY
     * @param j column
     * @param i line
     * @return
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
        return null;
        //

    }

}
