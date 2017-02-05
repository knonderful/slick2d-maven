package slickng.gfx;

import java.nio.ByteBuffer;

/**
 * A container for (raw) image data.
 */
public interface ImageData {

  /**
   * Retrieves the raw image data buffer. The data in this buffer must be in the
   * format specified by {@link #getPixelFormat()}.
   *
   * @return The byte buffer.
   */
  ByteBuffer getData();

  /**
   * Retrieves the width of the image.
   *
   * @return The width in pixels.
   */
  int getImageWidth();

  /**
   * Retrieves the height of the image.
   *
   * @return The height in pixels.
   */
  int getImageHeight();

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
