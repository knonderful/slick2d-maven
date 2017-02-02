package slickng;

public interface Graphics {

  RenderContext getRenderContext();

  /**
   * Retrieves a {@link SurfaceFactory} for creating surfaces for this graphics
   * implementation.
   *
   * @return The {@link SurfaceFactory}.
   */
  SurfaceFactory getSurfaceFactory();
}
