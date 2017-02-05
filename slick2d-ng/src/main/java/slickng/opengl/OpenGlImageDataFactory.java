package slickng.opengl;

import slickng.ImageData;
import slickng.ImageDataFactory;
import slickng.PixelFormat;

/**
 * {@link ImageDataFactory} for the OpenGL renderer.
 */
class OpenGlImageDataFactory implements ImageDataFactory {

  OpenGlImageDataFactory() {
  }

  @Override
  public ImageData create(PixelFormat pixelFormat, int width, int height) {
    return new OpenGlImageData(pixelFormat, width, height);
  }
}
