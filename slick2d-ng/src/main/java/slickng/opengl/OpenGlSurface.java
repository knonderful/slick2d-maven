package slickng.opengl;

import slickng.gfx.ImageData;
import slickng.gfx.Surface;
import slickng.gfx.TileSheet;

import static java.util.Objects.requireNonNull;

/**
 * The {@link Surface} implementation for the OpenGL renderer.
 */
class OpenGlSurface implements Surface {

  private final OpenGlTexture texture;

  static OpenGlSurface fromImageData(SGL sgl, ImageData imageData) {
    return new OpenGlSurface(OpenGlTexture.create(sgl, imageData));
  }

  private OpenGlSurface(OpenGlTexture texture) {
    this.texture = requireNonNull(texture, "Argument texture must be non-null.");
  }

  @Override
  public TileSheet createTileSheet(int tileWidth, int tileHeight) {
    return new OpenGlTileSheet(tileWidth, tileHeight, getTextureWidth(), getTextureHeight());
  }

  @Override
  public int getHeight() {
    return texture.getHeight();
  }

  int getTextureHeight() {
    return texture.getTextureHeight();
  }

  int getTextureWidth() {
    return texture.getTextureWidth();
  }

  @Override
  public int getWidth() {
    return texture.getWidth();
  }

  /**
   * Binds the {@link OpenGlTexture} to the OpenGL context. This method must be
   * called in order to use the {@link OpenGlSurface} for rendering.
   *
   * @param sgl The {@link SGL}.
   */
  void bind(SGL sgl) {
    texture.bind(sgl);
  }
}
