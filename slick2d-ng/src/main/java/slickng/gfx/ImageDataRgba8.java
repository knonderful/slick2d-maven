package slickng.gfx;

/**
 * RGBA with 8 bits per pixel.
 */
public class ImageDataRgba8 extends AbstractImageDataDecorator {

  /**
   * Retrieves the {@link PixelFormat} for this {@link ImageData}.
   *
   * @return The {@link PixelFormat}.
   */
  public static PixelFormat getPixelFormat() {
    return PixelFormat.RGBA_8;
  }

  /**
   * Creates a new instance.
   *
   * @param decorated The decorated {@link ImageData}.
   */
  public ImageDataRgba8(ImageData decorated) {
    super(decorated);
  }

}
