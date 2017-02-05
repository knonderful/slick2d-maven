package slickng.gfx;

/**
 * A 2D graphic that can be rendered using a {@link SurfaceRenderer}.
 */
public interface Sprite {

  /**
   * Renders the sprite at the provided location.
   *
   * @param renderer The {@link SurfaceRenderer}.
   * @param x        The X-position at which to render the sprite.
   * @param y        The Y-position at which to render the sprite.
   */
  public void render(SurfaceRenderer renderer, float x, float y);
}
