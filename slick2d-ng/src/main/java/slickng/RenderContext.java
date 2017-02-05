package slickng;

import java.util.function.Consumer;
import slickng.gfx.Renderer2D;
import slickng.gfx.Surface;

/**
 * The context for {@link Game#render(slickng.RenderContext)}.
 */
public interface RenderContext {

  /**
   * Performs rendering operations with the provided {@link Surface}.
   *
   * @param surface  The {@link Surface}.
   * @param consumer The {@link Renderer2D} consumer.
   */
  void with(Surface surface, Consumer<Renderer2D> consumer);

  /**
   * Scales the context.
   *
   * @param x The horizontal scale factor.
   * @param y The vertical scale factor.
   */
  void scale(float x, float y);
}
