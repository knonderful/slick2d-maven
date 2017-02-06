package slickng.opengl;

import slickng.gfx.Tile;
import slickng.gfx.TileSheet;

/**
 * {@link TileSheet} implementation for the OpenGL renderer.
 */
class OpenGlTileSheet implements TileSheet {

  private final int width;
  private final int height;
  private final int tileWidth;
  private final int tileHeight;
  private final int textureWidth;
  private final int textureHeight;
  private final Tile[][] cache;

  /**
   * Creates a new instance.
   *
   * @param surface       The surface.
   * @param tileWidth     The width of each tile.
   * @param tileHeight    The height of each tile.
   * @param textureWidth  The width of the OpenGL texture.
   * @param textureHeight The height of the OpenGL texture.
   */
  OpenGlTileSheet(int tileWidth, int tileHeight, int surfaceWidth, int surfaceHeight, int textureWidth, int textureHeight) {
    this.tileWidth = tileWidth;
    this.tileHeight = tileHeight;
    this.textureWidth = textureWidth;
    this.textureHeight = textureHeight;

    this.width = surfaceWidth / tileWidth;
    this.height = surfaceHeight / tileHeight;

    this.cache = new Tile[width][height];
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Tile getTile(int indexX, int indexY) {
    if (indexX >= width) {
      throw new IllegalArgumentException(String.format("Argument indexX (%d) is out of range for the tile sheet (%dx%d).", indexX, width, height));
    }
    if (indexX >= height) {
      throw new IllegalArgumentException(String.format("Argument indexY (%d) is out of range for the tile sheet (%dx%d).", indexX, width, height));
    }

    // Get tile from the cache, if available
    Tile tile = cache[indexX][indexY];
    if (tile != null) {
      return tile;
    }

    tile = new OpenGlTile(indexX * tileWidth, indexY * tileHeight, tileWidth, tileHeight, textureWidth, textureHeight);
    cache[indexX][indexY] = tile;
    return tile;
  }

  @Override
  public int getTileHeight() {
    return tileHeight;
  }

  @Override
  public int getTileWidth() {
    return tileWidth;
  }

  @Override
  public int getWidth() {
    return width;
  }

}
