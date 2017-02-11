package slickng.lwjgl.gfx;

import java.util.Objects;
import slickng.gfx.Surface;
import slickng.gfx.TileSheet;

import static java.util.Objects.requireNonNull;

/**
 * The {@link Surface} implementation for the OpenGL renderer.
 */
class OpenGlSurface implements Surface {

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
    if (!Objects.equals(this.texture, other.texture)) {
      return false;
    }
    return true;
  }

  @Override
  public int getHeight() {
    return texture.getHeight();
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
   * Binds the {@link OpenGlTexture} to the OpenGL context. This method must be
   * called in order to use the {@link OpenGlSurface} for rendering.
   */
  void bind() {
    texture.bind();
  }
}
