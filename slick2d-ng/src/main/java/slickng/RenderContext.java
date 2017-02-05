package slickng;

import slickng.gfx.SurfaceRenderer;
import slickng.gfx.Surface;
import java.util.function.Consumer;

/**
 * The context for {@link Game#render(slickng.RenderContext)}.
 */
public interface RenderContext {

  /**
   * Performs rendering operations with the provided {@link Surface}.
   *
   * @param surface  The {@link Surface}.
   * @param consumer The {@link SurfaceRenderer} consumer.
   */
  void with(Surface surface, Consumer<SurfaceRenderer> consumer);

}
