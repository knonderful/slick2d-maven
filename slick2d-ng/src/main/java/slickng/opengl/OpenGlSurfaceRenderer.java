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
    renderFragment(x, y, width, height, 0f, 0f, surface.getWidth(), surface.getHeight());
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
    sgl.glTexCoord2f(textOffX, textOffY);
    sgl.glVertex3f(x, y, 0);
    sgl.glTexCoord2f(textOffX, textOffY + textHeight);
    sgl.glVertex3f(x, y + height, 0);
    sgl.glTexCoord2f(textOffX + textWidth, textOffY + textHeight);
    sgl.glVertex3f(x + width, y + height, 0);
    sgl.glTexCoord2f(textOffX + textWidth, textOffY);
    sgl.glVertex3f(x + width, y, 0);
  }

}
