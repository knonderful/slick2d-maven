package slickng.opengl;

import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import slickng.gfx.ImageData;
import slickng.gfx.PixelFormat;

import static org.lwjgl.BufferUtils.createIntBuffer;

/**
 * An OpenGL texture.
 * <p>
 * Note that a texture must be bound to the OpenGL context by calling
 * {@link #bind(slickng.opengl.SGL)} before it can be used. At most one texture
 * can be bound at a time.
 */
class OpenGlTexture {

  private static final int TARGET = GL11.GL_TEXTURE_2D;
  private static final PixelFormat EXPECTED_PIXEL_FORMAT = PixelFormat.RGBA;
  private static final int SRC_PIXEL_FORMAT = GL11.GL_RGBA;
  private static final int DEST_PIXEL_FORMAT = GL11.GL_RGBA8;

  private final int textureId;
  private final int width;
  private final int height;
  private final int textureWidth;
  private final int textureHeight;

  static OpenGlTexture create(ImageData imageData) {
    // This can only happen if an ImageData from another renderer gets passed in (or if there is a programmatical error).
    if (imageData.getPixelFormat() != EXPECTED_PIXEL_FORMAT) {
      throw new IllegalArgumentException(String.format("This implementation only supports pixel format %s, but received %s.", EXPECTED_PIXEL_FORMAT, imageData.getPixelFormat()));
    }

    int textureId = createTextureID();
    GL11.glBindTexture(TARGET, textureId);

    GL11.glTexParameteri(TARGET, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
    GL11.glTexParameteri(TARGET, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

    // produce a texture from the byte buffer
    GL11.glTexImage2D(TARGET,
            0,
            DEST_PIXEL_FORMAT,
            imageData.getTextureWidth(),
            imageData.getTextureHeight(),
            0,
            SRC_PIXEL_FORMAT,
            GL11.GL_UNSIGNED_BYTE,
            imageData.getData());

    return new OpenGlTexture(textureId, imageData.getImageWidth(), imageData.getImageHeight(), imageData.getTextureWidth(), imageData.getTextureHeight());
  }

  private static int createTextureID() {
    IntBuffer tmp = createIntBuffer(1);
    GL11.glGenTextures(tmp);
    return tmp.get(0);
  }

  private OpenGlTexture(int textureId, int width, int height, int textureWidth, int textureHeight) {
    this.textureId = textureId;
    this.width = width;
    this.height = height;
    this.textureWidth = textureWidth;
    this.textureHeight = textureHeight;
  }

  int getHeight() {
    return height;
  }

  int getTextureId() {
    return textureId;
  }

  int getTextureWidth() {
    return textureWidth;
  }

  int getTextureHeight() {
    return textureHeight;
  }

  int getWidth() {
    return width;
  }

  /**
   * Binds the {@link OpenGlTexture} to the OpenGL context.
   */
  void bind() {
    GL11.glBindTexture(TARGET, textureId);
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
