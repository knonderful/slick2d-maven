package slickng.opengl;

import slickng.ImageData;
import slickng.Surface;
import slickng.SurfaceFactory;

import static java.util.Objects.requireNonNull;

/**
 * {@link SurfaceFactory} for the OpenGL renderer.
 */
class OpenGlSurfaceFactory implements SurfaceFactory {

  private final SGL sgl;

  OpenGlSurfaceFactory(SGL sgl) {
    this.sgl = requireNonNull(sgl, "Argument sgl must be non-null.");
  }

  @Override
  public Surface create(ImageData imageData) {
    return OpenGlSurface.fromImageData(sgl, imageData);
  }

  @Override
  public void release(Surface surface) {
    // Does nothing right now
  }

}
