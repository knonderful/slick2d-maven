package slickng.gfx;

/**
 * A tile represents a rectangular region of a {@link Surface}.
 */
public interface Tile {

  /**
   * Retrieves the X-coordinate of the offset of the tile in the underlying
   * {@link Surface}.
   *
   * @return The X-coordinate.
   */
  int getOffsetX();

  /**
   * Retrieves the Y-coordinate of the offset of the tile in the underlying
   * {@link Surface}.
   *
   * @return The Y-coordinate.
   */
  int getOffsetY();

  /**
   * Retrieves the width.
   *
   * @return The width.
   */
  int getWidth();

  /**
   * Retrieves the height.
   *
   * @return The height.
   */
  int getHeight();
}
