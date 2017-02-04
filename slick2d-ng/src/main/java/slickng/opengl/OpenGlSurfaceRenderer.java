package slickng.opengl;

import slickng.SurfaceRenderer;

class OpenGlSurfaceRenderer implements SurfaceRenderer {

  private final SGL sgl;
  private final OpenGlSurface surface;

  OpenGlSurfaceRenderer(SGL sgl, OpenGlSurface surface) {
    this.sgl = sgl;
    this.surface = surface;
  }

  @Override
  public void render(float x, float y) {
    render(x, y, surface.getWidth(), surface.getHeight());
  }

  @Override
  public void render(float x, float y, float width, float height) {
    renderFragmentInternal(x, y, x + width, y + height, 0f, 0f, 1f, 1f);
  }

  @Override
  public void renderFragment(float x, float y, float fragOffX, float fragOffY, float fragWidth, float fragHeight) {
    renderFragment(x, y, fragWidth, fragHeight, fragOffX, fragOffY, fragWidth, fragHeight);
  }

  @Override
  public void renderFragment(float x, float y, float width, float height, float fragOffX, float fragOffY, float fragWidth, float fragHeight) {
    float textOffX = fragOffX / surface.getTextureWidth();
    float textWidth = fragWidth / surface.getTextureWidth();
    float textOffY = fragOffY / surface.getTextureHeight();
    float textHeight = fragHeight / surface.getTextureHeight();

    renderFragmentInternal(
            x, y, x + width, y + height,
            textOffX, textOffY, textOffX + textWidth, textOffY + textHeight);
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
    sgl.glTexCoord2f(tx1, ty1);
    sgl.glVertex3f(x1, y1, 0);
    sgl.glTexCoord2f(tx2, ty2);
    sgl.glVertex3f(x2, y2, 0);
    sgl.glTexCoord2f(tx3, ty3);
    sgl.glVertex3f(x3, y3, 0);
    sgl.glTexCoord2f(tx4, ty4);
    sgl.glVertex3f(x4, y4, 0);
  }
}