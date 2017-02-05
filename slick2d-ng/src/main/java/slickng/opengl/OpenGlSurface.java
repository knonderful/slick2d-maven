package slickng.opengl;

import slickng.ImageData;
import slickng.Surface;
import slickng.TileSheet;

import static java.util.Objects.requireNonNull;

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

  public int getTextureHeight() {
    return texture.getTextureHeight();
  }

  public int getTextureWidth() {
    return texture.getTextureWidth();
  }

  @Override
  public int getWidth() {
    return texture.getWidth();
  }

  void bind(SGL sgl) {
    texture.bind(sgl);
  }
}
