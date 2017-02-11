package slickng.lwjgl.gfx;

import slickng.gfx.PixelFormat;

import static java.util.Objects.requireNonNull;

class OpenGlImageBufferFactory {

  private final OpenGlByteBufferFactory byteBufferFactory;

  OpenGlImageBufferFactory(OpenGlByteBufferFactory byteBufferFactory) {
    this.byteBufferFactory = requireNonNull(byteBufferFactory, "Argument byteBufferFactory must be non-null.");
  }

  OpenGlImageBuffer create(PixelFormat pixelFormat, int width, int height) {
    return new OpenGlImageBuffer(byteBufferFactory, pixelFormat, width, height);
  }

  void release(OpenGlImageBuffer imageBuffer) {
    imageBuffer.release();
  }
}
