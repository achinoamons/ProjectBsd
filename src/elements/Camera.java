package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;


/**
 * Camera class -Infrastructure for a scene
 *
 * @author Achinoam  and Yael
 */
public class Camera {
    /**
     * camara location
     */
    final private Point3D _p0;
    /**
     * camera orientation vectors
     */
    final private Vector _vRIGHT;
    final private Vector _vUP;
    final private Vector _vTO;
    /**
     * depth of field
     */
    private double _width;
    private double _height;
    private double _distance;

    /**
     * getters
     *
     * @return the relevant fields
     */

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

    /**
     * * camera constructor
     *
     * @param p0  point of camara locetion
     * @param vUP orientation vectors
     * @param vTO orientation vectors
     */
    public Camera(Point3D p0,  Vector vTO,Vector vUP) {
        _p0 = p0;
        _vUP = vUP.normalized();
        _vTO = vTO.normalized();
        if (!isZero(vTO.dotProduct(vUP))) {
            throw new IllegalArgumentException("vup is not orthogonal to vto");
        }

        _vRIGHT = _vTO.crossProduct(_vUP);

    }

    /**
     * @param width-field
     * @param height-field
     * @return the camera-builder pattern
     */
    public Camera setViewPlaneSize(double width, double height) {
        _height = height;
        _width = width;
        return this;
    }

    /**
     * @param distance-field
     * @return the camera-builder pattern(chaining)
     */
    public Camera setDistance(double distance) {
        _distance = distance;
        return this;
    }

    /**
     * @param nX-NUMBER OF PIXELS -IN THE width
     * @param nY-NUMBER OF PIXELS -in the length
     * @param j         column
     * @param i         line
     * @return RAY
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {

        //calculate pc
        Point3D PC = _p0.add(_vTO.scale(_distance));
        //Ratio (pixel width & height)
        double Ry = _height / nY;
        double Rx = _width / nX;
        //Pixel[i,j] center
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
        Point3D Pij = PC;
//        if (!isZero(Xj) ) {
//            Pij = Pij.add(_vRIGHT.scale(Xj));
//        }
//        if (!isZero(Yi)) {
//            Pij = Pij.add(_vUP.scale(Yi));
//        }

        //if both are zero
        if(isZero(Xj)&&isZero(Yi)){
            return new Ray(_p0,Pij.subtract(_p0));
        }
        //only 1 is zero
        if(isZero(Xj)){
            Pij=PC.add(_vUP.scale(Yi));
            return new Ray(_p0,Pij.subtract(_p0));

        }

        if(isZero(Yi)){
            Pij=PC.add(_vRIGHT.scale(Xj));
            return new Ray(_p0,Pij.subtract(_p0));
        }

        //IF NO ONE IS ZERO

        Pij=PC.add(_vRIGHT.scale(Xj).add(_vUP.scale(Yi)));
        Vector Vij = Pij.subtract(_p0);
        return new Ray(_p0, Vij);



    }

}
