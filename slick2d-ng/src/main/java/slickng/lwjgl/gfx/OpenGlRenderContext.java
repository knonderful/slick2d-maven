package slickng.lwjgl.gfx;

import slickng.RenderContext;
import slickng.SlickException;
import slickng.gfx.Renderer2D;

/**
 * The {@link RenderContext} for the OpenGL renderer.
 */
class OpenGlRenderContext implements RenderContext {

  private final OpenGlIndexedSurfaceProgram paletteProgram = new OpenGlIndexedSurfaceProgram(
          OpenGlSurfaceRenderer.IMAGE_TEXTURE_UNIT,
          OpenGlSurfaceRenderer.PALETTE_TEXTURE_UNIT);
  private final OpenGlSurfaceRenderer renderer2d = new OpenGlSurfaceRenderer(paletteProgram);

  OpenGlRenderContext() {
  }

  @Override
  public Renderer2D getRenderer2D() {
    return renderer2d;
  }

  void init() throws SlickException {
    paletteProgram.init();
  }

  void deinit() {
    paletteProgram.deinit();
  }

}
