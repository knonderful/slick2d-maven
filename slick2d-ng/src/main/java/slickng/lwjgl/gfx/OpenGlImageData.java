package slickng.lwjgl.gfx;

import slickng.gfx.ImageData;
import slickng.gfx.PixelFormat;

import static java.util.Objects.requireNonNull;

/**
 * {@link ImageData} implementation for the OpenGL renderer.
 */
public class OpenGlImageData implements ImageData {

  private final OpenGlImageBufferFactory factory;
  private final OpenGlImageBuffer buffer;

  OpenGlImageData(OpenGlImageBufferFactory factory, PixelFormat pixelFormat, int width, int height) {
    this.factory = requireNonNull(factory, "Argument factory must be non-null.");
    this.buffer = factory.create(pixelFormat, width, height);
  }

  @Override
  public OpenGlSurface createSurface() {
    return OpenGlSurface.fromImageData(buffer);
  }

  @Override
  public OpenGlImageBuffer getBuffer() {
    return buffer;
  }

  void release() {
    factory.release(buffer);
  }
}
