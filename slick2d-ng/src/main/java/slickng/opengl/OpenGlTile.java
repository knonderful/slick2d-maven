package slickng.opengl;

import slickng.Tile;

/**
 * {@link Tile} implementation for the OpenGL renderer.
 */
class OpenGlTile implements Tile {

  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final float tx1;
  private final float ty1;
  private final float tx2;
  private final float ty2;

  /**
   * Creates a new instance.
   *
   * @param x             The X-coordinate of the top-left point of the tile in
   *                      the surface.
   * @param y             The Y-coordinate of the top-left point of the tile in
   *                      the surface.
   * @param width         The width of the tile.
   * @param height        The height of the tile.
   * @param textureWidth  The width of the OpenGL texture.
   * @param textureHeight The height of the OpenGL texture.
   */
  OpenGlTile(int x, int y, int width, int height, int textureWidth, int textureHeight) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;

    this.tx1 = (float) x / textureWidth;
    this.ty1 = (float) y / textureHeight;
    this.tx2 = (float) (x + width) / textureWidth;
    this.ty2 = (float) (y + height) / textureHeight;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getOffsetX() {
    return x;
  }

  @Override
  public int getOffsetY() {
    return y;
  }

  @Override
  public int getWidth() {
    return width;
  }

  /**
   * Returns the top-left texture X-coordinate.
   *
   * @return The coordinate.
   */
  float getTx1() {
    return tx1;
  }

  /**
   * Returns the top-left texture Y-coordinate.
   *
   * @return The coordinate.
   */
  float getTx2() {
    return tx2;
  }

  /**
   * Returns the bottom-right texture X-coordinate.
   *
   * @return The coordinate.
   */
  float getTy1() {
    return ty1;
  }

  /**
   * Returns the bottom-right texture Y-coordinate.
   *
   * @return The coordinate.
   */
  float getTy2() {
    return ty2;
  }

}
