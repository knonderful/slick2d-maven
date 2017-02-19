package slickng.gfx;

/**
 * A renderer for two-dimensional operations.
 */
public interface Renderer2D {

  /**
   * Saves the renderer state. The state can be restored by calling
   * {@link #restoreState()}.
   *
   * @return This {@link Renderer2D}.
   */
  Renderer2D saveState();

  /**
   * Restores the previously saved renderer state.
   *
   * @return This {@link Renderer2D}.
   */
  Renderer2D restoreState();

  /**
   * Applies a scale transformation.
   *
   * @param x The horizontal scaling factor.
   * @param y The vertical scaling factor.
   * @return This {@link Renderer2D}.
   */
  Renderer2D scale(float x, float y);

  /**
   * Applies a translate transformation.
   *
   * @param x The horizontal translation.
   * @param y The vertical translation.
   * @return This {@link Renderer2D}.
   */
  Renderer2D translate(float x, float y);

  /**
   * Applies a rotate transformation.
   *
   * @param angle The angle in degrees.
   * @return This {@link Renderer2D}.
   */
  Renderer2D rotate(float angle);

  /**
   * Sets the current image.
   *
   * @param image The image.
   * @return This {@link Renderer2D}.
   */
  Renderer2D setImage(Surface image);

  /**
   * Renders the current palette.
   *
   * @param palette The palette.
   * @return This {@link Renderer2D}.
   */
  Renderer2D setPalette(Surface palette);

  /**
   * Sets the offset in the current palette. This offset will be added to all
   * indices in an indexed image when rendering.
   *
   * @param x The horizontal offset.
   * @param y The vertical offset.
   * @return This {@link Renderer2D}.
   */
  Renderer2D setPaletteOffset(int x, int y);

  /**
   * Renders the current image.
   *
   * @return This {@link Renderer2D}.
   */
  Renderer2D render();

  /**
   * Renders the current image.
   *
   * @param width  The width with which to render the image.
   * @param height The height with which to render the image.
   * @return This {@link Renderer2D}.
   */
  Renderer2D render(float width, float height);

  /**
   * Renders a tile from the current image.
   *
   * @param tile The {@link Tile}.
   * @return This {@link Renderer2D}.
   */
  Renderer2D render(Tile tile);
}
