package slickng.opengl;

import java.util.function.Consumer;
import slickng.RenderContext;
import slickng.Surface;

import static java.util.Objects.requireNonNull;

class OpenGlRenderContext implements RenderContext {

  private final SGL sgl;

  OpenGlRenderContext(SGL sgl) {
    this.sgl = requireNonNull(sgl, "Argument sgl must be non-null.");
  }

  @Override
  public void with(Surface surface, Consumer<SurfaceOperations> consumer) {
    OpenGlSurface surf = castSurface(surface);
    surf.bind(sgl);

    sgl.glBegin(SGL.GL_QUADS);
    consumer.accept(new OpenGlSurfaceOperations(sgl, surf));
    sgl.glEnd();
  }

  private OpenGlSurface castSurface(Surface surface) {
    return (OpenGlSurface) surface;
  }

  private static class OpenGlSurfaceOperations implements SurfaceOperations {

    private final SGL sgl;
    private final OpenGlSurface surface;

    OpenGlSurfaceOperations(SGL sgl, OpenGlSurface surface) {
      this.sgl = sgl;
      this.surface = surface;
    }

    @Override
    public void render(float x, float y) {
      render(x, y, surface.getWidth(), surface.getHeight());
    }

    @Override
    public void render(float x, float y, float width, float height) {
      render(x, y, width, height, 0f, 0f, surface.getWidth(), surface.getHeight());
    }

    @Override
    public void render(float x, float y, float width, float height, float fragOffX, float fragOffY, float fragWidth, float fragHeight) {
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
}
