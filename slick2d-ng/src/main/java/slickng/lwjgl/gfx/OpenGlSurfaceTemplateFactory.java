package slickng.lwjgl.gfx;

import slickng.Lease;
import slickng.Leases;
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

  private OpenGlSurfaceTemplate createImageData(PixelFormat pixelFormat, int width, int height) {
    return new OpenGlSurfaceTemplate(imageBufferFactory, pixelFormat, width, height);
  }

  OpenGlSurfaceTemplateFactory(OpenGlImageBufferFactory imageBufferFactory) {
    this.imageBufferFactory = requireNonNull(imageBufferFactory, "Argument imageBufferFactory must be non-null.");
  }

  @Override
  public Lease<SurfaceTemplate> create(PixelFormat pixelFormat, int width, int height) throws UnsupportedFormatException {
    OpenGlSurfaceTemplate template = createImageData(pixelFormat, width, height);
    return Leases.createLease(template, tmp -> ((OpenGlSurfaceTemplate) tmp).release());
  }
}
