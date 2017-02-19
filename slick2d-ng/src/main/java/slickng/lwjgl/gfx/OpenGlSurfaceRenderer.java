package slickng.lwjgl.gfx;

import slickng.gfx.Renderer2D;
import slickng.gfx.Surface;
import slickng.gfx.Tile;

import static java.util.Objects.requireNonNull;
import static org.lwjgl.opengl.GL11.*;

/**
 * The {@link Renderer2D} implementation for the OpenGL renderer.
 */
class OpenGlSurfaceRenderer implements Renderer2D {

  static final int IMAGE_TEXTURE_UNIT = 0;
  static final int PALETTE_TEXTURE_UNIT = 1;
  private final OpenGlIndexedSurfaceProgram paletteProgram;
  private OpenGlSurface image;
  private OpenGlSurface palette;

  OpenGlSurfaceRenderer(OpenGlIndexedSurfaceProgram paletteProgram) {
    this.paletteProgram = requireNonNull(paletteProgram, "Argument paletteProgram must be non-null.");
  }

  @Override
  public OpenGlSurfaceRenderer render() {
    render(image.getWidth(), image.getHeight());
    return this;
  }

  @Override
  public OpenGlSurfaceRenderer render(float width, float height) {
    renderFragmentInternal(0f, 0f, width, height, 0f, 0f, width / image.getTextureWidth(), height / image.getTextureHeight());
    return this;
  }

  @Override
  public OpenGlSurfaceRenderer render(Tile tile) {
    OpenGlTile t = castTile(tile);
    renderFragmentInternal(
            0, 0, t.getWidth(), t.getHeight(),
            t.getTx1(), t.getTy1(), t.getTx2(), t.getTy2());

    return this;
  }

  @Override
  public Renderer2D rotate(float angle) {
    glRotatef(angle, 0f, 0f, 1f);
    return this;
  }

  @Override
  public Renderer2D scale(float x, float y) {
    glScalef(x, y, 1f);
    return this;
  }

  @Override
  public Renderer2D setImage(Surface image) {
    this.image = castSurface(image);

    // Only use the palette shader program if the current image requires it
    if (this.image.getPixelFormat().isIndexed()) {
      paletteProgram.bind();
    } else {
      paletteProgram.unbind();
    }

    // Bind the image
    this.image.bind(IMAGE_TEXTURE_UNIT);

    return this;
  }

  @Override
  public Renderer2D setPalette(Surface palette) {
    this.palette = castSurface(palette);

    // Bind the palette
    this.palette.bind(PALETTE_TEXTURE_UNIT);

    return this;
  }

  @Override
  public Renderer2D setPaletteOffset(int x, int y) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Renderer2D translate(float x, float y) {
    glTranslatef(x, y, 0f);
    return this;
  }

  private static OpenGlSurface castSurface(Surface surface) {
    return (OpenGlSurface) surface;
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

    glBegin(GL_QUADS);
    glTexCoord2f(tx1, ty1);
    glVertex3f(x1, y1, 0);
    glTexCoord2f(tx2, ty2);
    glVertex3f(x2, y2, 0);
    glTexCoord2f(tx3, ty3);
    glVertex3f(x3, y3, 0);
    glTexCoord2f(tx4, ty4);
    glVertex3f(x4, y4, 0);
    glEnd();
  }

}
