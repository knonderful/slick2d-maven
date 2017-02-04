package slickng.opengl;

import slickng.ImageData;
import slickng.Surface;

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
  public float getHeight() {
    return texture.getHeight();
  }

  public int getTextureHeight() {
    return texture.getTextureHeight();
  }

  public int getTextureWidth() {
    return texture.getTextureWidth();
  }

  @Override
  public float getWidth() {
    return texture.getWidth();
  }

  void bind(SGL sgl) {
    texture.bind(sgl);
  }
}
