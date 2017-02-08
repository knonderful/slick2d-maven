package slickng.gfx;

import static java.util.Objects.requireNonNull;

/**
 * Default implementation of {@link ImageData}.
 */
public class DefaultImageData implements ImageData {

  private final ImageBuffer buffer;
  private final PixelFormat pixelFormat;

  /**
   * Creates a new instance.
   *
   * @param bufferFactory The {@link ImageBufferFactory}.
   * @param width         The image width.
   * @param height        The image height.
   * @param pixelFormat   The {@link PixelFormat}.
   */
  protected DefaultImageData(ImageBufferFactory bufferFactory, int width, int height, PixelFormat pixelFormat) {
    this.buffer = bufferFactory.create(width, height, pixelFormat.getBytesPerPixel());
    this.pixelFormat = requireNonNull(pixelFormat, "Argument pixelFormat must be non-null.");
  }

  @Override
  public ImageBuffer getBuffer() {
    return buffer;
  }

  @Override
  public PixelFormat getPixelFormat() {
    return pixelFormat;
  }
}
