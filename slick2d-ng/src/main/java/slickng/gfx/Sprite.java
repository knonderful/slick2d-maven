package slickng.gfx;

/**
 * A 2D graphic that can be rendered using a {@link Renderer2D}.
 */
public interface Sprite {

  /**
   * Renders the sprite at the provided location.
   *
   * @param renderer The {@link Renderer2D}.
   * @param x        The X-position at which to render the sprite.
   * @param y        The Y-position at which to render the sprite.
   */
  public void render(Renderer2D renderer, float x, float y);
}
