package slickng.opengl;

import java.util.function.Consumer;
import org.lwjgl.opengl.GL11;
import slickng.RenderContext;
import slickng.gfx.Renderer2D;
import slickng.gfx.Surface;

/**
 * The {@link RenderContext} for the OpenGL renderer.
 */
class OpenGlRenderContext implements RenderContext {

  OpenGlRenderContext() {
  }

  @Override
  public void scale(float x, float y) {
    GL11.glScalef(x, y, 1f);
  }

  @Override
  public void with(Surface surface, Consumer<Renderer2D> consumer) {
    OpenGlSurface surf = castSurface(surface);
    surf.bind();

    GL11.glBegin(GL11.GL_QUADS);
    consumer.accept(new OpenGlSurfaceRenderer(surf));
    GL11.glEnd();
  }

  private OpenGlSurface castSurface(Surface surface) {
    return (OpenGlSurface) surface;
  }
}
