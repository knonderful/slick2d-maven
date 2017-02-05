package slickng.opengl;

import java.util.function.Consumer;
import slickng.RenderContext;
import slickng.gfx.Renderer2D;
import slickng.gfx.Surface;

import static java.util.Objects.requireNonNull;

/**
 * The {@link RenderContext} for the OpenGL renderer.
 */
class OpenGlRenderContext implements RenderContext {

  private final SGL sgl;

  OpenGlRenderContext(SGL sgl) {
    this.sgl = requireNonNull(sgl, "Argument sgl must be non-null.");
  }

  @Override
  public void scale(float x, float y) {
    sgl.glScalef(x, y, 1f);
  }

  @Override
  public void with(Surface surface, Consumer<Renderer2D> consumer) {
    OpenGlSurface surf = castSurface(surface);
    surf.bind(sgl);

    sgl.glBegin(SGL.GL_QUADS);
    consumer.accept(new OpenGlSurfaceRenderer(sgl, surf));
    sgl.glEnd();
  }

  private OpenGlSurface castSurface(Surface surface) {
    return (OpenGlSurface) surface;
  }
}
