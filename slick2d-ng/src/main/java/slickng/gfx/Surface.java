package slickng.gfx;

/**
 * A two-dimensional surface that contains graphics information.
 */
public interface Surface {

  /**
   * Retrieves the width of the surface.
   *
   * @return The width in pixels.
   */
  int getWidth();

  /**
   * Retrieves the height of the surface.
   *
   * @return The height in pixels.
   */
  int getHeight();

  /**
   * Creates a {@link TileSheet} for the surface.
   *
   * @param tileWidth  The tile width.
   * @param tileHeight The tile height.
   * @return The {@link TileSheet}.
   */
  TileSheet createTileSheet(int tileWidth, int tileHeight);
}
