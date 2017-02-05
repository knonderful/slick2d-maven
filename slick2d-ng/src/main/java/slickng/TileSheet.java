package slickng;

/**
 * A sheet of {@link Tile}s. Each tile has the same dimensions.
 * <p>
 * The tiles are organized in a two-dimensional raster. Each individual
 * {@link Tile} can be retrieved through {@link #getTile(int, int)}.
 */
public interface TileSheet {

  /**
   * Retrieves the tile at the specified raster position.
   *
   * @param indexX The horizontal index in the raster.
   * @param indexY The the vertical index in the raster.
   * @return The {@link Tile}.
   * @throws IllegalArgumentException If either one of the provided indices is
   *                                  outside the raster.
   */
  Tile getTile(int indexX, int indexY);

  /**
   * Retrieves the width of a {@link Tile}.
   *
   * @return The width.
   */
  int getTileWidth();

  /**
   * Retrieves the height of a {@link Tile}.
   *
   * @return The height.
   */
  int getTileHeight();
}
