package slickng.lwjgl.gfx;

import slickng.gfx.Renderer2D;
import slickng.gfx.Tile;

import static org.lwjgl.opengl.GL11.*;

/**
 * The {@link Renderer2D} implementation for the OpenGL renderer.
 */
class OpenGlSurfaceRenderer implements Renderer2D {

  private final OpenGlSurface surface;

  OpenGlSurfaceRenderer(OpenGlSurface surface) {
    this.surface = surface;
  }

  @Override
  public void render(float x, float y) {
    render(x, y, surface.getWidth(), surface.getHeight());
  }

  @Override
  public void render(float x, float y, float width, float height) {
    renderFragmentInternal(x, y, x + width, y + height, 0f, 0f, width / surface.getTextureWidth(), height / surface.getTextureHeight());
  }

  /**
   * Renders a fragment of the surface with the provided location. The render
   * target will have the size of the fragment.
   *
   * @param x          The X-position at which to render.
   * @param y          The Y-position at which to render.
   * @param fragOffX   The X-offset of the fragment in the surface.
   * @param fragOffY   The Y-offset of the fragment in the surface.
   * @param fragWidth  The width of the fragment.
   * @param fragHeight The heigh of the fragment.
   */
  public void renderFragment(float x, float y, float fragOffX, float fragOffY, float fragWidth, float fragHeight) {
    renderFragment(x, y, fragWidth, fragHeight, fragOffX, fragOffY, fragWidth, fragHeight);
  }

  /**
   * Renders a fragment of the surface with the provided location and
   * dimensions.
   *
   * @param x          The X-position at which to render.
   * @param y          The Y-position at which to render.
   * @param width      The width of the render target.
   * @param height     The height of the render target.
   * @param fragOffX   The X-offset of the fragment in the surface.
   * @param fragOffY   The Y-offset of the fragment in the surface.
   * @param fragWidth  The width of the fragment.
   * @param fragHeight The heigh of the fragment.
   */
  public void renderFragment(float x, float y, float width, float height, float fragOffX, float fragOffY, float fragWidth, float fragHeight) {
    float textOffX = fragOffX / surface.getTextureWidth();
    float textWidth = fragWidth / surface.getTextureWidth();
    float textOffY = fragOffY / surface.getTextureHeight();
    float textHeight = fragHeight / surface.getTextureHeight();

    renderFragmentInternal(
            x, y, x + width, y + height,
            textOffX, textOffY, textOffX + textWidth, textOffY + textHeight);
  }

  @Override
  public void renderTile(Tile tile, float x, float y) {
    OpenGlTile t = castTile(tile);
    renderFragmentInternal(
            x, y, x + t.getWidth(), y + t.getHeight(),
            t.getTx1(), t.getTy1(), t.getTx2(), t.getTy2());
  }

  private static OpenGlTile castTile(Tile tile) {
    return (OpenGlTile) tile;
  }

  private void renderFragmentInternal(float x, float y, float x2, float y2,
          float tx, float ty, float tx2, float ty2) {

    // Counter-clockwise rendering
    renderInternal(
            x, y, x, y2, x2, y2, x2, y,
            tx, ty, tx, ty2, tx2, ty2, tx2, ty);
  }

  private void renderInternal(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4,
          float tx1, float ty1, float tx2, float ty2, float tx3, float ty3, float tx4, float ty4) {
    glTexCoord2f(tx1, ty1);
    glVertex3f(x1, y1, 0);
    glTexCoord2f(tx2, ty2);
    glVertex3f(x2, y2, 0);
    glTexCoord2f(tx3, ty3);
    glVertex3f(x3, y3, 0);
    glTexCoord2f(tx4, ty4);
    glVertex3f(x4, y4, 0);
  }
}
