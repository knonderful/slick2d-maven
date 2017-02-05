package slickng.gfx;

/**
 * A factory of {@link Surface} instances.
 */
public interface SurfaceFactory {

  /**
   * Creates a new surface from the provided image data.
   *
   * @param imageData The image data.
   * @return The surface.
   */
  Surface create(ImageData imageData);

  /**
   * Releases the surface.
   * <p>
   * After calling this method, the surface should no longer be referenced.
   *
   * @param surface The surface.
   */
  void release(Surface surface);
}