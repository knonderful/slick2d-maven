package slickng.gfx;

/**
 * Factory for creating {@link ImageBuffer} instances.
 */
public interface ImageBufferFactory {

  /**
   * Creates a new {@link ImageBuffer} with the provided image width, height and
   * bytes per pixel.
   *
   * @param width         The image width.
   * @param height        The image height.
   * @param bytesPerPixel The bytes per pixel.
   * @return The {@link ImageBuffer}.
   */
  ImageBuffer create(int width, int height, int bytesPerPixel);
}
