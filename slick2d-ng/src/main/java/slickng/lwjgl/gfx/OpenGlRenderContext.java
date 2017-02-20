package slickng.lwjgl.gfx;

import slickng.RenderContext;
import slickng.SlickException;
import slickng.gfx.Renderer2D;

/**
 * The {@link RenderContext} for the OpenGL renderer.
 */
class OpenGlRenderContext implements RenderContext {

  private final OpenGlIndexedSurfaceProgram paletteProgram = new OpenGlIndexedSurfaceProgram(
          OpenGlRenderer2D.IMAGE_TEXTURE_UNIT,
          OpenGlRenderer2D.PALETTE_TEXTURE_UNIT);
  private final OpenGlRenderer2D renderer2d = new OpenGlRenderer2D(paletteProgram);

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
