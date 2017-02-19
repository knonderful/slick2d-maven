package slickng.lwjgl.gfx;

import java.nio.IntBuffer;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import slickng.gfx.PixelFormat;

import static org.lwjgl.BufferUtils.createIntBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.GL_R8;

/**
 * An OpenGL texture.
 * <p>
 * Note that a texture must be bound to the OpenGL context by calling
 * {@link #bind(slickng.opengl.SGL)} before it can be used. At most one texture
 * can be bound at a time.
 */
class OpenGlTexture {

  private static final int TARGET = GL_TEXTURE_2D;
  private static final Map<PixelFormat, FormatPair> PIXEL_FORMAT_MAP = createPixelFormatMap();

  private final int textureId;
  private final int width;
  private final int height;
  private final int textureWidth;
  private final int textureHeight;
  private final PixelFormat pixelFormat;

  static OpenGlTexture create(OpenGlImageBuffer imageBuffer) {
    int textureId = createTextureID();
    glBindTexture(TARGET, textureId);

    glTexParameteri(TARGET, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(TARGET, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

    PixelFormat pixelFormat = imageBuffer.getPixelFormat();

    FormatPair pair = PIXEL_FORMAT_MAP.get(pixelFormat);
    if (pair == null) {
      throw new IllegalStateException(String.format("Pixel format %s not supported by graphics implementation.", pixelFormat));
    }

    // produce a texture from the byte buffer
    glTexImage2D(TARGET,
            0,
            pair.getDestFormat(),
            imageBuffer.getSurfaceWidth(),
            imageBuffer.getSurfaceHeight(),
            0,
            pair.getSrcFormat(),
            GL_UNSIGNED_BYTE,
            imageBuffer.rewindAndGetData());

    return new OpenGlTexture(textureId, imageBuffer.getImageWidth(), imageBuffer.getImageHeight(), imageBuffer.getSurfaceWidth(), imageBuffer.getSurfaceHeight(), pixelFormat);
  }

  private static Map<PixelFormat, FormatPair> createPixelFormatMap() {
    Map<PixelFormat, FormatPair> pixelFormatMap = new EnumMap<>(PixelFormat.class);
    pixelFormatMap.put(PixelFormat.RGBA_8, new FormatPair(GL_RGBA, GL_RGBA8));
    pixelFormatMap.put(PixelFormat.INDEXED_8, new FormatPair(GL_RED, GL_R8));
    return Collections.unmodifiableMap(pixelFormatMap);
  }

  private static int createTextureID() {
    IntBuffer tmp = createIntBuffer(1);
    glGenTextures(tmp);
    return tmp.get(0);
  }

  private OpenGlTexture(int textureId, int width, int height, int textureWidth, int textureHeight, PixelFormat pixelFormat) {
    this.textureId = textureId;
    this.width = width;
    this.height = height;
    this.textureWidth = textureWidth;
    this.textureHeight = textureHeight;
    this.pixelFormat = pixelFormat;
  }

  PixelFormat getPixelFormat() {
    return pixelFormat;
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
   *
   * @param textureUnit The target texture unit (starting at zero).
   */
  void bind(int textureUnit) {
    glActiveTexture(GL_TEXTURE0 + textureUnit);
    glBindTexture(TARGET, textureId);
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

  private static class FormatPair {

    private final int srcFormat;
    private final int destFormat;

    FormatPair(int srcFormat, int destFormat) {
      this.srcFormat = srcFormat;
      this.destFormat = destFormat;
    }

    int getDestFormat() {
      return destFormat;
    }

    int getSrcFormat() {
      return srcFormat;
    }
  }
}
