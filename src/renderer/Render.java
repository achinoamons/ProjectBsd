package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;

import java.util.List;
import java.util.MissingResourceException;

/**
 * @author Yael and Achinoam
 * class Whose function is to create from the scene the color matrix of the image
 */
public class Render {
    ImageWriter _imageWriter = null;
    Camera _camera = null;
    RayTracerBase _rayTracerBase = null;
    private boolean isAntialiacing=false;
    private boolean isDepthOfField=false;

    public boolean isDepthOfField() {
        return isDepthOfField;
    }

    public Render setDepthOfField(boolean depthOfField) {
        isDepthOfField = depthOfField;
        return this;
    }

    public boolean isAntialiacing() {
        return isAntialiacing;
    }

    public Render setAntialiacing(boolean antialiacing) {
        isAntialiacing = antialiacing;
        return this;
    }

    //chaining methods that return the object itself (render)
    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }
//כנל ביטול לפי ההנחיות
//    public Render setScene(Scene scene) {
//        _scene = scene;
//        return this;
//    }

    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase rayTracer) {
        _rayTracerBase = rayTracer;
        return this;
    }

    public void renderImage() {
        try {
            if (_imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            //כנל לפי הנחיות
//            if (_scene == null) {
//                throw new MissingResourceException("missing resource", Scene.class.getName(), "");
//            }
            if (_camera == null) {
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
            }
            if (_rayTracerBase == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }
            //if all fields not null-do this:
            //render the image
            Color pixelcolor = Color.BLACK;
            int nX = _imageWriter.getNx();
            int nY = _imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    //create the ray
                    if (!isAntialiacing&&!isDepthOfField) {//if there is no improvement
                        Ray ray = _camera.constructRayThroughPixel(nX, nY, j, i);
                        //color the closest point to the ray (if exist) or background...
                        pixelcolor = _rayTracerBase.traceRay(ray);

                    }
                    else if(isDepthOfField) {
                       List<Ray>  rays = _camera.constructBeamRay(nX, nY, j, i);
                        //color the closest point to the ray (if exist) or background...
                        for (var r : rays) {
                            Color rcolor =_rayTracerBase.traceRay(r);
                            pixelcolor= pixelcolor.add(rcolor);
                        }
                        pixelcolor=pixelcolor.scale(1d/rays.size());
                    }
                    else if (isAntialiacing){//anti true

                        List<Ray> rays = _camera.constructRaysThroughPixel(nX,nY,j,i);
                        for (var r : rays) {
                            Color rcolor =_rayTracerBase.traceRay(r);
                           pixelcolor= pixelcolor.add(rcolor);
                        }
                        pixelcolor=pixelcolor.scale(1d/rays.size());
                    }
                    //go and color it in the image
                    _imageWriter.writePixel(j, i, pixelcolor);


                }
            }

        } catch (MissingResourceException e) {//if something is missing you cannot continue
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    /**
     * create the image
     */
    public void writeToImage() {
        try {
            if (_imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        _imageWriter.writeToImage();
    }

    /**
     * color grid of lines
     *
     * @param interval for calculate if it "on the grid"
     * @param color    -the color to color the grid
     */
    public void printGrid(int interval, Color color) {
        try {
            if (_imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(j, i, color);
                }
            }
        }
    }
}
