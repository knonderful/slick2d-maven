package slickng.lwjgl.gfx;

import slickng.UnsupportedFormatException;
import slickng.gfx.PixelFormat;
import slickng.gfx.SurfaceTemplate;
import slickng.gfx.SurfaceTemplateFactory;

import static java.util.Objects.requireNonNull;

/**
 * {@link SurfaceTemplateFactory} for the OpenGL renderer.
 */
class OpenGlSurfaceTemplateFactory implements SurfaceTemplateFactory {

  private final OpenGlImageBufferFactory imageBufferFactory;

  OpenGlSurfaceTemplateFactory(OpenGlImageBufferFactory imageBufferFactory) {
    this.imageBufferFactory = requireNonNull(imageBufferFactory, "Argument imageBufferFactory must be non-null.");
  }

  @Override
  public SurfaceTemplate create(PixelFormat pixelFormat, int width, int height) throws UnsupportedFormatException {
    return new OpenGlSurfaceTemplate(imageBufferFactory, pixelFormat, width, height);
  }
}
