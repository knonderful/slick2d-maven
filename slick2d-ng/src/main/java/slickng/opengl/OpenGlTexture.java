package slickng.opengl;

import java.nio.IntBuffer;
import slickng.ImageData;
import slickng.PixelFormat;

import static org.lwjgl.BufferUtils.createIntBuffer;

class OpenGlTexture {

  private static final int TARGET = SGL.GL_TEXTURE_2D;
  private static final PixelFormat EXPECTED_PIXEL_FORMAT = PixelFormat.RGBA;
  private static final int SRC_PIXEL_FORMAT = SGL.GL_RGBA;
  private static final int DEST_PIXEL_FORMAT = SGL.GL_RGBA8;

  private final int textureId;

  static OpenGlTexture create(SGL sgl, ImageData imageData) {
    // This can only happen if an ImageData from another renderer gets passed in (or if there is a programmatical error).
    if (imageData.getPixelFormat() != EXPECTED_PIXEL_FORMAT) {
      throw new IllegalArgumentException(String.format("This implementation only supports pixel format %s, but received %s.", EXPECTED_PIXEL_FORMAT, imageData.getPixelFormat()));
    }

    int textureId = createTextureID(sgl);
    sgl.glBindTexture(TARGET, textureId);

    sgl.glTexParameteri(TARGET, SGL.GL_TEXTURE_MIN_FILTER, SGL.GL_LINEAR);
    sgl.glTexParameteri(TARGET, SGL.GL_TEXTURE_MAG_FILTER, SGL.GL_LINEAR);

    // produce a texture from the byte buffer
    sgl.glTexImage2D(TARGET,
            0,
            DEST_PIXEL_FORMAT,
            imageData.getTextureWidth(),
            imageData.getTextureHeight(),
            0,
            SRC_PIXEL_FORMAT,
            SGL.GL_UNSIGNED_BYTE,
            imageData.getData());

    return new OpenGlTexture(textureId);
  }

  private static int createTextureID(SGL sgl) {
    IntBuffer tmp = createIntBuffer(1);
    sgl.glGenTextures(tmp);
    return tmp.get(0);
  }

  private OpenGlTexture(int textureId) {
    this.textureId = textureId;
  }

  void bind(SGL sgl) {
    sgl.glBindTexture(TARGET, textureId);
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
    final OpenGlTexture other = (OpenGlTexture) obj;
    return this.textureId == other.textureId;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 83 * hash + this.textureId;
    return hash;
  }

}
