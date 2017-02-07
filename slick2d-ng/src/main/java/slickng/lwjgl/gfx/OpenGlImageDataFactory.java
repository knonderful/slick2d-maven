package slickng.lwjgl.gfx;

import slickng.gfx.ImageData;
import slickng.gfx.ImageDataFactory;
import slickng.gfx.PixelFormat;

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

  @Override
  public void release(ImageData imageData) {
    // Nothing to be done.
  }
}
