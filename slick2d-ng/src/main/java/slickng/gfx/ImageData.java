package slickng.gfx;

/**
 * A container for image data.
 */
public interface ImageData {

  /**
   * Retrieves the {@link ImageBuffer}.
   *
   * @return The {@link ImageBuffer}.
   */
  ImageBuffer getBuffer();

  /**
   * Creates a new {@link Surface}.
   *
   * @return The {@link Surface}.
   */
  Surface createSurface();
}
