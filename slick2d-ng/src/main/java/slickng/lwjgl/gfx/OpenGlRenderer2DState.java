package slickng.lwjgl.gfx;

/**
 * A state in the {@link OpenGlSurfaceRenderer}.
 * <p>
 * Note that this class is designed to work closely together with
 * {@link OpenGlSurfaceRenderer}. It is presumed that the state that is being
 * restores is always the last state that was saved. This assumption allows the
 * implementation to avoid unnecessary tracking for internals (such as the
 * transformation matrices).
 */
public class OpenGlRenderer2DState {

  private final OpenGlSurface image;
  private final OpenGlSurface palette;
  private final int paletteOffsetX;
  private final int paletteOffsetY;

  private OpenGlRenderer2DState(OpenGlSurface image, OpenGlSurface palette, int paletteOffsetX, int paletteOffsetY) {
    this.image = image;
    this.palette = palette;
    this.paletteOffsetX = paletteOffsetX;
    this.paletteOffsetY = paletteOffsetY;
  }

  void restore(OpenGlSurfaceRenderer renderer) {
    renderer.setImage(image)
            .setPalette(palette)
            .setPaletteOffset(paletteOffsetX, paletteOffsetY)
            .popMatrix();
  }

  static OpenGlRenderer2DState save(OpenGlSurfaceRenderer r) {
    r.pushMatrix();
    return new OpenGlRenderer2DState(
            r.getImage(),
            r.getPalette(),
            r.getPaletteOffsetX(),
            r.getPaletteOffsetY());
  }
}
