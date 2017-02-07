package slickng.lwjgl.gfx;

import slickng.gfx.ImageData;
import slickng.gfx.Surface;
import slickng.gfx.SurfaceFactory;

/**
 * {@link SurfaceFactory} for the OpenGL renderer.
 */
class OpenGlSurfaceFactory implements SurfaceFactory {

  OpenGlSurfaceFactory() {
  }

  @Override
  public Surface create(ImageData imageData) {
    return OpenGlSurface.fromImageData(imageData);
  }

  @Override
  public void release(Surface surface) {
    // Does nothing right now
  }

}
