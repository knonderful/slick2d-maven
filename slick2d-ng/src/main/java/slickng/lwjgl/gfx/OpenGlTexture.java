package slickng.lwjgl.gfx;

import java.nio.IntBuffer;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import slickng.gfx.PixelFormat;

import static org.lwjgl.BufferUtils.createIntBuffer;
import static org.lwjgl.opengl.GL11.*;

/**
 * An OpenGL texture.
 * <p>
 * Note that a texture must be bound to the OpenGL context by calling
 * {@link #bind(slickng.opengl.SGL)} before it can be used. At most one texture
 * can be bound at a time.
 */
class OpenGlTexture {

  private static final int TARGET = GL_TEXTURE_2D;
  private static final PixelFormat EXPECTED_PIXEL_FORMAT = PixelFormat.RGBA_8;
  private static final int SRC_PIXEL_FORMAT = GL_RGBA;
  private static final int DEST_PIXEL_FORMAT = GL_RGBA8;

  private static final Map<PixelFormat, Integer> PIXEL_FORMAT_MAP = createPixelFormatMap();

  private final int textureId;
  private final int width;
  private final int height;
  private final int textureWidth;
  private final int textureHeight;

  static OpenGlTexture create(OpenGlImageBuffer imageBuffer) {
    int textureId = createTextureID();
    glBindTexture(TARGET, textureId);

    glTexParameteri(TARGET, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(TARGET, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

    // produce a texture from the byte buffer
    glTexImage2D(TARGET,
            0,
            toOpenGl(imageBuffer.getPixelFormat()),
            imageBuffer.getSurfaceWidth(),
            imageBuffer.getSurfaceHeight(),
            0,
            SRC_PIXEL_FORMAT,
            GL_UNSIGNED_BYTE,
            imageBuffer.rewindAndGetData());

    return new OpenGlTexture(textureId, imageBuffer.getImageWidth(), imageBuffer.getImageHeight(), imageBuffer.getSurfaceWidth(), imageBuffer.getSurfaceHeight());
  }

  private static Map<PixelFormat, Integer> createPixelFormatMap() {
    Map<PixelFormat, Integer> pixelFormatMap = new EnumMap<>(PixelFormat.class);
    pixelFormatMap.put(PixelFormat.RGBA_8, GL_RGBA8);
    return Collections.unmodifiableMap(pixelFormatMap);
  }

  private static int toOpenGl(PixelFormat pixelFormat) {
    Integer openGlFormat = PIXEL_FORMAT_MAP.get(pixelFormat);
    if (openGlFormat == null) {
      throw new IllegalStateException(String.format("Pixel format %s not supported by graphics implementation.", pixelFormat));
    }
    return openGlFormat;
  }

  private static int createTextureID() {
    IntBuffer tmp = createIntBuffer(1);
    glGenTextures(tmp);
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

}
