package slickng.gfx;

/**
 * A container for image data.
 */
public interface ImageData {

  /**
   * Retrieves the {@link PixelFormat}.
   *
   * @return The {@link PixelFormat}.
   */
  PixelFormat getPixelFormat();

  /**
   * Retrieves the {@link ImageBuffer}.
   *
   * @return The {@link ImageBuffer}.
   */
  ImageBuffer getBuffer();

}
