package slickng;

import slickng.gfx.Renderer2D;

/**
 * The context for {@link Game#render(slickng.RenderContext)}.
 */
public interface RenderContext {

  /**
   * Retrieves the {@link Renderer2D}.
   *
   * @return The {@link Renderer2D}.
   */
  Renderer2D getRenderer2D();
}
