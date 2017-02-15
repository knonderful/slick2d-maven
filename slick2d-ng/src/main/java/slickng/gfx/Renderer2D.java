package slickng.gfx;

/**
 * A renderer for two-dimensional operations.
 */
public interface Renderer2D {

  void setPaletteIndex(int index);

  /**
   * Renders the entire surface with the provided location.
   *
   * @param x The X-position at which to render.
   * @param y The Y-position at which to render.
   */
  void render(float x, float y);

  /**
   * Renders the entire surface with the provided location and dimensions.
   *
   * @param x      The X-position at which to render.
   * @param y      The Y-position at which to render.
   * @param width  The width of the render target.
   * @param height The height of the render target.
   */
  void render(float x, float y, float width, float height);

  /**
   * Renders a {@link Tile} at the provided location.
   *
   * @param tile The {@link Tile} to render.
   * @param x    The X-position at which to render.
   * @param y    The Y-position at which to render.
   */
  void renderTile(Tile tile, float x, float y);
}
