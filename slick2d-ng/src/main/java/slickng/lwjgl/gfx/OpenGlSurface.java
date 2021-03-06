package slickng.lwjgl.gfx;

import java.util.Objects;
import slickng.gfx.PixelFormat;
import slickng.gfx.Surface;
import slickng.gfx.TileSheet;

import static java.util.Objects.requireNonNull;

/**
 * The {@link Surface} implementation for the OpenGL renderer.
 */
public class OpenGlSurface implements Surface {

  private final OpenGlTexture texture;

  static OpenGlSurface fromImageData(OpenGlImageBuffer imageBuffer) {
    return new OpenGlSurface(OpenGlTexture.create(imageBuffer));
  }

  private OpenGlSurface(OpenGlTexture texture) {
    this.texture = requireNonNull(texture, "Argument texture must be non-null.");
  }

  @Override
  public TileSheet createTileSheet(int tileWidth, int tileHeight) {
    return new OpenGlTileSheet(tileWidth, tileHeight, getWidth(), getHeight(), getTextureWidth(), getTextureHeight());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final OpenGlSurface other = (OpenGlSurface) obj;
    return Objects.equals(this.texture, other.texture);
  }

  @Override
  public int getHeight() {
    return texture.getHeight();
  }

  @Override
  public PixelFormat getPixelFormat() {
    return texture.getPixelFormat();
  }

  public OpenGlTexture getTexture() {
    return texture;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 17 * hash + Objects.hashCode(this.texture);
    return hash;
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
   * Binds the {@link OpenGlTexture} to the OpenGL context.
   *
   * @param  textureUnit The target texture unit (starting at zero).
   */
  void bind(int textureUnit) {
    texture.bind(textureUnit);
  }
}
