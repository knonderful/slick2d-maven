package slickng.gfx;

/**
 * RGBA with 8 bits per pixel.
 */
public class ImageDataRgba8 extends DefaultImageData {

  /**
   * Creates a new instance.
   *
   * @param bufferFactory The {@link ImageBufferFactory} for creating the
   *                      {@link ImageBuffer}.
   * @param width         The width of the image.
   * @param height        The height of the image.
   */
  public ImageDataRgba8(ImageBufferFactory bufferFactory, int width, int height) {
    super(bufferFactory, width, height, PixelFormat.RGBA_8);
  }
}
