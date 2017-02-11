package slickng.lwjgl.gfx;

import slickng.UnsupportedFormatException;
import slickng.gfx.ImageData;
import slickng.gfx.ImageDataFactory;
import slickng.gfx.ImageDataRgba8;

import static java.util.Objects.requireNonNull;

/**
 * {@link ImageDataFactory} for the OpenGL renderer.
 */
class OpenGlImageDataFactory implements ImageDataFactory {

  private final OpenGlImageBufferFactory imageBufferFactory;

  OpenGlImageDataFactory(OpenGlImageBufferFactory imageBufferFactory) {
    this.imageBufferFactory = requireNonNull(imageBufferFactory, "Argument imageBufferFactory must be non-null.");
  }

  @Override
  public <T extends ImageData> T create(Class<T> type, int width, int height) throws UnsupportedFormatException {
    if (type.isAssignableFrom(ImageDataRgba8.class)) {
      OpenGlImageData imageData = new OpenGlImageData(imageBufferFactory, ImageDataRgba8.getPixelFormat(), width, height);
      return type.cast(new ImageDataRgba8(imageData));
    }

    throw new UnsupportedFormatException(String.format("%s not supported by this factory.", type.getName()));
  }

  @Override
  public void release(ImageData imageData) {
    ((OpenGlImageData) imageData).release();
  }
}
