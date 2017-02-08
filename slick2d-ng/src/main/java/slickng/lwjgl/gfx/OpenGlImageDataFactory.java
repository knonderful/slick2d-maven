package slickng.lwjgl.gfx;

import slickng.UnsupportedFormatException;
import slickng.gfx.ImageData;
import slickng.gfx.ImageDataFactory;
import slickng.gfx.ImageDataRgba8;

/**
 * {@link ImageDataFactory} for the OpenGL renderer.
 */
class OpenGlImageDataFactory implements ImageDataFactory {

  private final OpenGlImageBufferFactory bufferFactory = new OpenGlImageBufferFactory();

  OpenGlImageDataFactory() {
  }

  @Override
  public <T extends ImageData> T create(Class<T> type, int width, int height) throws UnsupportedFormatException {
    if (type.isAssignableFrom(ImageDataRgba8.class)) {
      return type.cast(new ImageDataRgba8(bufferFactory, width, height));
    }

    throw new UnsupportedFormatException(String.format("%s not supported by this factory.", type.getName()));
  }

  @Override
  public void release(ImageData imageData) {
    // Nothing to be done.
  }
}
