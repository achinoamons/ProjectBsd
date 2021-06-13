package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import renderer.Render;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static primitives.Util.isZero;
import static primitives.Util.random;


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
    private final static Random rnd = new Random();

    /**
     * camera orientation vectors
     */
    final private Vector _vRIGHT;
    final private Vector _vUP;
    final private Vector _vTO;

    private double _width;
    private double _height;
    private double _distance;
    private int sizeGrid =1;


    public int getSizeGrid() {
        return sizeGrid;
    }

    public Camera setSizeGrid(int sizeGrid) {
        this.sizeGrid = sizeGrid;
        return this;
    }
    //
        private boolean isAntialiacing=false;
    private boolean isDepthOfField=false;

    public boolean isDepthOfField() {
        return isDepthOfField;
    }

    public Camera setDepthOfField(boolean depthOfField) {
        isDepthOfField = depthOfField;
        return this;
    }

    public boolean isAntialiacing() {
        return isAntialiacing;
    }

    public Camera setAntialiacing(boolean antialiacing) {
        isAntialiacing = antialiacing;
        return this;
    }

    //

    //////////////////depthOfField
    /**
     * size For Depth Of Field<br>
     * the size of aperture window(rectangle)
     */
    private double sizeForApertureWindow = 50;
    /**
     * distance For Depth Of Filed <br>
     * Distance from camera to Focal plane
     */
    private double distanceToFocalPlane = 0;
    /**
     * num Of Ray Form Aperture Window To Focal Point<br>
     * Number of rays for focus
     */
    private int numOfRayFormApertureWindowToFocalPoint = 0;

    /**
     * @param distancForDepthOfFilde the distancForDepthOfFilde to set
     */
    public Camera setDistanceToFocalPlane(double distancForDepthOfFilde) {
        if (distancForDepthOfFilde < 0)
            throw new IllegalArgumentException("distanc For Depth Of Filde cant be less then 0!");
        this.distanceToFocalPlane = distancForDepthOfFilde;
        return this;
    }

    /**
     * @param radius the radius to set
     */
    public Camera setSizeForApertureWindow(double radius) {
        if (radius < 0)
            throw new IllegalArgumentException("radius For Depth Of Field cant be less then 0!");
        this.sizeForApertureWindow = radius;
        return this;

    }

    /**
     * @param numOfRayForDepthOfFil the number of ray
     */
    public Camera setNumOfRayFormApertureWindowToFocalPoint(int numOfRayForDepthOfFil) {
        if (numOfRayForDepthOfFil < 0)
            throw new IllegalArgumentException("num Of Ray For Depth Of Filde cant be less then 0!");
        this.numOfRayFormApertureWindowToFocalPoint = numOfRayForDepthOfFil;
        return this;

    }


    //////////////////////////////////////

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
    public Camera(Point3D p0, Vector vTO, Vector vUP) {
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
//random antialiacing without grid

//    public List<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i) {
//        double Rx = _width / nX;//the length of pixel in X axis
//        double Ry = _height / nY;//the length of pixel in Y axis
//
//        Point3D Pij = getPij(nX, nY, j, i, _width, _height, _distance);
//
//        //-----SuperSampling-----
//        List<Ray> rays = new LinkedList<>();//the return list, construct Rays Through Pixels
//         rays.add(new Ray(_p0,Pij.subtract(_p0)));
//        Point3D tmp;
//        for(int k=0;k<sizeGrid;k++)
//        {
//           double randx=random(-Rx/2,Rx/2);
//            double randy=random(-Ry/2,Ry/2);
//            tmp=new Point3D(Pij.getX()+randx,Pij.getY()+randy,Pij.getZ());
//           rays.add(new Ray(_p0, tmp.subtract(_p0)));
//
//        }
//
//
//        return rays;
//    }

    //random with grid
    public List<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i) {
        Ray centerRay = constructRayThroughPixel(nX,nY,j,i);
//        double t = _distance / (_vTO.dotProduct(centerRay.getDir()));
//        List<Ray> rays = centerRay.raySplitter(sizeGrid,_width/nX,t);
//        return rays;



        double Rx = _width / nX;//the length of pixel in X axis
        double Ry = _height / nY;//the length of pixel in Y axis

        Point3D Pij = getPij(nX, nY, j, i, _width, _height, _distance);
        Point3D tmp;
        //-----SuperSampling-----
        List<Ray> rays = new LinkedList<>();//the return list, construct Rays Through Pixels


        double n = Math.floor(Math.sqrt(sizeGrid));
        int delta = (int) (n / 2d);//התזוזות ממרכז הפיקסל
        //אורך ורוחב של כל "תת" פיקסל
        double gapX = Rx / n;
        double gapY = Ry / n;

/* ***********************************************************************
             |(-3,-3)|(-3,-2)|(-3,-1)|(-3, 0)|(-3, 1)|(-3, 2)||(-3, 3)
             |(-2,-3)|(-2,-2)|(-2,-1)|(-2, 0)|(-2, 1)|(-2, 2)||(-2, 3)
             |(-1,-3)|(-1,-2)|(-1,-1)|(-1, 0)|(-1, 1)|(-1, 2)||(-1, 3)
             |( 0,-3)|( 0,-2)|( 0,-1)|( 0, 0)|( 0, 1)|( 0, 2)||( 0, 3)
             |( 1,-3)|( 1,-2)|( 1,-1)|( 1, 0)|( 1, 1)|( 1, 2)||( 1, 3)
             |( 2,-3)|( 2,-2)|( 2,-1)|( 2, 0)|( 2, 1)|( 2, 2)||( 2, 3)
             |( 3,-3)|( 3,-2)|( 3,-1)|( 3, 0)|( 3, 1)|( 3, 2)||( 3, 3)
*************************************************************************** */
        for (int row = -delta; row <= delta; row++) {
            for (int col = -delta; col <= delta; col++) {
                tmp = new Point3D(Pij.getX(),Pij.getY(),Pij.getZ());//מרכז הפיקסל הספציפי
                if (!isZero(row)) {//אם אני לא בפיקסל המרכזי
                    tmp = tmp.add(_vRIGHT.scale(row * (double) Math.random()*((gapX))-(gapX)/2));//(double) Math.random()*((gapX))-gapX/2))
                    //tmp = tmp.add(_vRIGHT.scale(row * gapX));
                }
                if (!isZero(col)) {
                    tmp = tmp.add(_vRIGHT.scale(col * (double) Math.random()*((gapY))-(gapY)/2));//(double) Math.random()*((gapY))-gapY/2));
                    //tmp = tmp.add(_vRIGHT.scale(col * gapY));
                }
                rays.add(new Ray(_p0, tmp.subtract(_p0).normalize()));
            }
        }
        return rays;
    }

    private Point3D getPij(int nX, int nY, int j, int i, double width, double height, double distance) {
        //calculate pc
        Point3D PC = _p0.add(_vTO.scale(_distance));
        //Ratio (pixel width & height)
        double Ry = _height / nY;
        double Rx = _width / nX;
        //Pixel[i,j] center
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
        Point3D Pij = PC;


        //if both are zero
        if (isZero(Xj) && isZero(Yi)) {
           return Pij;
        }
        //only 1 is zero
        if (isZero(Xj)) {
            Pij = PC.add(_vUP.scale(Yi));
            return Pij;


        }

        if (isZero(Yi)) {
            Pij = PC.add(_vRIGHT.scale(Xj));
            return Pij;
        }

        //IF NO ONE IS ZERO

        Pij = PC.add(_vRIGHT.scale(Xj).add(_vUP.scale(Yi)));
        return Pij;
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
        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(_p0, Pij.subtract(_p0));
        }
        //only 1 is zero
        if (isZero(Xj)) {
            Pij = PC.add(_vUP.scale(Yi));
            return new Ray(_p0, Pij.subtract(_p0));

        }

        if (isZero(Yi)) {
            Pij = PC.add(_vRIGHT.scale(Xj));
            return new Ray(_p0, Pij.subtract(_p0));
        }

        //IF NO ONE IS ZERO

        Pij = PC.add(_vRIGHT.scale(Xj).add(_vUP.scale(Yi)));
        Vector Vij = Pij.subtract(_p0);
        return new Ray(_p0, Vij);


    }
    ///for depth
    /**
     * construct beam of Ray Through Pixel form location of camera F
     *
     * @param ray for center ray
     * @return List<Ray> form radius For Depth Of Field towards the center of
     *         pixel<br>
     *
     */
    public List<Ray> constructBeamRayThroughFocalPoint(Ray ray, int nX, int nY) {
        if (numOfRayFormApertureWindowToFocalPoint == 0)
            return null;
        List<Ray> list = new LinkedList<>();
        double t = distanceToFocalPlane / (_vTO.dotProduct(ray.getDir()));
        list = ray.raySplitter(numOfRayFormApertureWindowToFocalPoint, sizeForApertureWindow, distanceToFocalPlane,
                ray.getPoint(t));
        return list;
    }

    /**
     * construct Ray Through Pixel form location of camera F
     *
     * @param nX depend hoe pixel we wont row
     * @param nY depend hoe pixel we wont column
     * @param j  Rows
     * @param i  Columns
     * @return Ray form location towards the center of pixel
     */
    public List<Ray> constructBeamRay(int nX, int nY, int j, int i) {
        Ray ray = constructRayThroughPixel(nX, nY, j, i);
        List<Ray> raysFocal = constructBeamRayThroughFocalPoint(ray, nX, nY);
        //List<Ray> raysAliesing = constructBeamRayForAntiAliesing(ray, nX, nY);
        List<Ray> rays = new LinkedList<>();
//        if (raysAliesing != null)
//            rays.addAll(raysAliesing);
        if (raysFocal != null)
            rays.addAll(raysFocal);
        rays.add(ray);
        return rays;
    }

}
