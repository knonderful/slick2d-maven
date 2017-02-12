package slickng.lwjgl.gfx;

import slickng.Lease;
import slickng.Leases;
import slickng.UnsupportedFormatException;
import slickng.gfx.ImageData;
import slickng.gfx.ImageDataFactory;
import slickng.gfx.ImageDataRgba8;
import slickng.gfx.PixelFormat;

import static java.util.Objects.requireNonNull;

/**
 * {@link ImageDataFactory} for the OpenGL renderer.
 */
class OpenGlImageDataFactory implements ImageDataFactory {

  private final OpenGlImageBufferFactory imageBufferFactory;

  private <T extends ImageData> T createImageData(Class<T> type, int width, int height) throws UnsupportedFormatException {
    if (type.isAssignableFrom(ImageDataRgba8.class)) {
      return type.cast(new ImageDataRgba8(createOpenGlImageData(ImageDataRgba8.getPixelFormat(), width, height)));
    }

    throw new UnsupportedFormatException(String.format("%s not supported by this factory.", type.getName()));
  }

  private OpenGlImageData createOpenGlImageData(PixelFormat pixelFormat, int width, int height) {
    return new OpenGlImageData(imageBufferFactory, pixelFormat, width, height);
  }

  OpenGlImageDataFactory(OpenGlImageBufferFactory imageBufferFactory) {
    this.imageBufferFactory = requireNonNull(imageBufferFactory, "Argument imageBufferFactory must be non-null.");
  }

  @Override
  public <T extends ImageData> Lease<T> create(Class<T> type, int width, int height) throws UnsupportedFormatException {
    T imageData = createImageData(type, width, height);
    return Leases.createLease(imageData, imgData -> imgData.release());
  }
}
