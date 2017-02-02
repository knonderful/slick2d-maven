package slickng;

import java.nio.ByteBuffer;

/**
 * A container for (raw) image data.
 */
public interface ImageData {

  /**
   * Retrieves the raw image data.
   * <p>
   * The data must be a buffer of unsigned bytes in RGBA format in the native
   * byte order of the current underlying platform.
   *
   * @return The byte buffer.
   */
  ByteBuffer getData();

  /**
   * Retrieves the width of the image.
   *
   * @return The width in pixels.
   */
  int getWidth();

  /**
   * Retrieves the height of the image.
   *
   * @return The height in pixels.
   */
  int getHeight();

  /**
   * Retrieves the width of the underlying texture.
   *
   * @return The width of the texture in pixels.
   */
  int getTextureWidth();

  /**
   * Retrieves the height of the underlying texture.
   *
   * @return The height of the texture in pixels.
   */
  int getTextureHeight();

  /**
   * Retrieves the pixel format for the underlying texture.
   *
   * @return The pixel format.
   */
  PixelFormat getPixelFormat();
}
