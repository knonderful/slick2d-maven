package slickng.gfx;

/**
 * A template for creating a {@link Surface}.
 * <p>
 * A {@link SurfaceTemplate} instance can be used to create multiple
 * {@link Surface}s. An already-created {@link Surface} is not affected by
 * changes that are made to the {@link SurfaceTemplate} after the
 * {@link Surface} was created.
 */
public interface SurfaceTemplate {

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
