//package renderer;
//
//import elements.Camera;
//import primitives.Color;
//import primitives.Ray;
//import scene.Scene;
//
//import java.util.MissingResourceException;
//
//public class Render {
//    ImageWriter _imageWriter=null;
//    Scene _scene=null;
//    Camera _camera=null;
//    RayTracerBase _rayTracerBase=null;
//
//    //chaining methods that return the object itself (render)
//    public Render setImageWriter(ImageWriter imageWriter) {
//        _imageWriter = imageWriter;
//        return this;
//    }
//
//    public Render setScene(Scene scene) {
//        _scene = scene;
//        return this;
//    }
//
//    public Render setCamera(Camera camera) {
//        _camera = camera;
//        return this;
//    }
//
//    public Render setRayTracer(RayTracerBase rayTracer) {
//        _rayTracerBase = rayTracer;
//        return this;
//    }
//    public void renderImage(){
//        try {
//            if (_imageWriter == null) {
//                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
//            }
//            if (_scene == null) {
//                throw new MissingResourceException("missing resource", Scene.class.getName(), "");
//            }
//            if (_camera == null) {
//                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
//            }
//            if (_rayTracerBase == null) {
//                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
//            }
//   //if all fields not null-do this:
//        //render the image
//            int nX=_imageWriter.getNx();
//            int nY=_imageWriter.getNy();
//            for (int i=0;i<nY;i++){
//                for (int j=0;j<nX;j++){
//                    //create the ray
//                    Ray ray=_camera.constructRayThroughPixel(nX,nY,j,i);
//                    //color the closest point to the ray (if exist) or background...
//                    Color pixelcolor=_rayTracerBase.traceRay(ray);
//                    //go andcolor it in the image
//                    _imageWriter.writePixel(j,i,pixelcolor);
//
//                }
//            }
//
//        } catch (MissingResourceException e) {//if something is missing you cannot continue
//            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
//        }
//    }
//
//    public void writeToImage(){
//       try{ if (_imageWriter == null) {
//            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
//        }
//    } catch (MissingResourceException e) {
//        throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
//    }
//        _imageWriter.writeToImage();
//    }
//
//    /**
//     * color grid of lines
//     * @param interval for calculate if it
//     * @param color -the color to color the grid
//     */
//    public void printGrid(int interval, Color color) {
//        try{ if (_imageWriter == null) {
//            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
//        }
//        } catch (MissingResourceException e) {
//            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
//        }
//        int nX = _imageWriter.getNx();
//        int nY = _imageWriter.getNy();
//        for (int i = 0; i < nY; i++) {
//            for (int j = 0; j < nX; j++) {
//                if (i % interval == 0 || j % interval == 0) {
//                    _imageWriter.writePixel(j, i, color);
//                }
//            }
//        }
//    }
//}
package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;

public class Render {
    ImageWriter _imageWriter = null;
    Scene _scene = null;
    Camera _camera = null;
    RayTracerBase _rayTracerBase = null;

    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    public Render setScene(Scene scene) {
        _scene = scene;
        return this;
    }

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
            if (_scene == null) {
                throw new MissingResourceException("missing resource", Scene.class.getName(), "");
            }
            if (_camera == null) {
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
            }
            if (_rayTracerBase == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }

            //rendering the image
            int nX = _imageWriter.getNx();
            int nY = _imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = _camera.constructRayThroughPixel(nX, nY, j, i);
                    Color pixelColor = _rayTracerBase.traceRay(ray);
                    _imageWriter.writePixel(j, i, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }


    public void printGrid(int interval, Color color) {
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

    public void writeToImage() {
        _imageWriter.writeToImage();
    }
}
