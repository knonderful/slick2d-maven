package slickng.lwjgl.gfx;

import java.util.function.Consumer;
import slickng.RenderContext;
import slickng.SlickException;
import slickng.gfx.Renderer2D;
import slickng.gfx.Surface;

import static org.lwjgl.opengl.GL11.*;

/**
 * The {@link RenderContext} for the OpenGL renderer.
 */
class OpenGlRenderContext implements RenderContext {

  private final OpenGlIndexedSurfaceProgram paletteProgram = new OpenGlIndexedSurfaceProgram();

  OpenGlRenderContext() {
  }

  void init() throws SlickException {
    paletteProgram.init();
  }

  void deinit() {
    paletteProgram.deinit();
  }

  @Override
  public void scale(float x, float y) {
    glScalef(x, y, 1f);
  }

  @Override
  public void with(Surface surface, Consumer<Renderer2D> consumer) {
    OpenGlSurface surf = castSurface(surface);
    surf.bind();

    glBegin(GL_QUADS);
    consumer.accept(new OpenGlSurfaceRenderer(surf));
    glEnd();
  }

  @Override
  public void with(Surface surface, Surface palette, Consumer<Renderer2D> consumer) {
    OpenGlSurface imageSurface = castSurface(surface);

    paletteProgram.bind();
    try {
      paletteProgram.setPalette(castSurface(palette));
      paletteProgram.setImage(imageSurface);

      glBegin(GL_QUADS);
      consumer.accept(new OpenGlSurfaceRenderer(imageSurface));
      glEnd();
    } finally {
      paletteProgram.unbind();
    }
  }

  private OpenGlSurface castSurface(Surface surface) {
    return (OpenGlSurface) surface;
  }
}
