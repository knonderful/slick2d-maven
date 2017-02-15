package slickng.gfx;

import java.io.IOException;
import java.io.InputStream;

/**
 * An {@link ImageBuffer} manages the raw data for an image.
 * <p>
 * The {@link ImageBuffer} distinguishes between the dimensions of the target
 * image and the dimensions of the underlying surface. This distinction is made
 * for graphics implementations that do not support arbitrary surface dimensions
 * under the hood. An example is OpenGL, which requires textures to be
 * dimensioned in powers of two. All {@link ImageBuffer} methods that write data
 * to the buffer handle the dimension constraints internally.
 */
public interface ImageBuffer {

  /**
   * Retrieves the {@link PixelFormat}.
   *
   * @return The {@link PixelFormat}.
   */
  PixelFormat getPixelFormat();

  /**
   * Reads image data from the input stream and writes it into the buffer.
   * <p>
   * This method will read from the input stream until enough data has been read
   * to write an entire image. If the end of the stream is reached before an
   * entire image is extracted, an {@link IOException} will be thrown.
   *
   * @param inputStream The input stream from which the data will be read.
   * @throws IOException If an I/O error occurs while reading from the input
   *                     stream.
   */
  void write(InputStream inputStream) throws IOException;

  /**
   * Writes a byte into the buffer and advances the cursor.
   *
   * @param value The value to write. Only the least-significant 8 bits of the
   *              provided value will be written.
   * @throws ArrayIndexOutOfBoundsException If the capacity of the underlying
   *                                        buffer has been exceeded.
   */
  void writeByte(int value);

  /**
   * Writes a byte into the buffer and advances the cursor.
   *
   * @param value The value to write.
   * @throws ArrayIndexOutOfBoundsException If the capacity of the underlying
   *                                        buffer has been exceeded.
   */
  void writeByte(byte value);

  /**
   * Reads a byte from the buffer and advances the cursor.
   *
   * @return The value that was read.
   */
  byte readByte();

  /**
   * Rewinds the cursor to the start of the buffer.
   */
  void rewind();

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
   * Retrieves the width of the underlying surface.
   *
   * @return The width in pixels.
   */
  int getSurfaceWidth();

  /**
   * Retrieves the height of the underlying surface.
   *
   * @return The height in pixels.
   */
  int getSurfaceHeight();
}
