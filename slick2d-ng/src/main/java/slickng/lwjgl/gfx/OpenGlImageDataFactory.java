package slickng.lwjgl.gfx;

import slickng.Lease;
import slickng.Leases;
import slickng.UnsupportedFormatException;
import slickng.gfx.ImageData;
import slickng.gfx.ImageDataFactory;
import slickng.gfx.PixelFormat;

import static java.util.Objects.requireNonNull;

/**
 * {@link ImageDataFactory} for the OpenGL renderer.
 */
class OpenGlImageDataFactory implements ImageDataFactory {

  private final OpenGlImageBufferFactory imageBufferFactory;

  private OpenGlImageData createImageData(PixelFormat pixelFormat, int width, int height) {
    return new OpenGlImageData(imageBufferFactory, pixelFormat, width, height);
  }

  OpenGlImageDataFactory(OpenGlImageBufferFactory imageBufferFactory) {
    this.imageBufferFactory = requireNonNull(imageBufferFactory, "Argument imageBufferFactory must be non-null.");
  }

  @Override
  public Lease<ImageData> create(PixelFormat pixelFormat, int width, int height) throws UnsupportedFormatException {
    OpenGlImageData imageData = createImageData(pixelFormat, width, height);
    return Leases.createLease(imageData, imgData -> ((OpenGlImageData) imgData).release());
  }
}
