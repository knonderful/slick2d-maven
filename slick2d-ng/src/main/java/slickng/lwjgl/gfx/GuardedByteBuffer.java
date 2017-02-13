package slickng.lwjgl.gfx;

import java.nio.ByteBuffer;

import static java.util.Objects.requireNonNull;

/**
 * A {@link ByteBuffer} wrapper that takes source image dimensions into account
 * when writing data.
 */
class GuardedByteBuffer {

  private final OpenGlByteBufferFactory factory;
  private final ByteBuffer byteBuffer;
  /**
   * The width of the writable area in bytes.
   */
  private final int writableWidth;
  /**
   * The height of the writable area in lines.
   */
  private final int writableHeight;
  /**
   * The number of bytes that should be skipped when the writable width has been
   * reached.
   */
  private final int marginWidth;
  private int xOffset = 0;
  private int yOffset = 0;

  GuardedByteBuffer(OpenGlByteBufferFactory factory, int imageWidth, int imageHeight, int surfaceWidth, int surfaceHeight, int bytesPerPixel) {
    this.factory = requireNonNull(factory, "Argument factory must be non-null.");
    this.writableWidth = imageWidth * bytesPerPixel;
    this.writableHeight = imageHeight;
    this.marginWidth = surfaceWidth * bytesPerPixel - imageWidth * bytesPerPixel;
    this.byteBuffer = factory.create(surfaceWidth * surfaceHeight * bytesPerPixel);
  }

  /**
   * Adds a byte to the buffer.
   *
   * @param b The byte.
   * @return The number of bytes that have been written.
   */
  void put(byte b) {
    putInternal(b);
  }

  /**
   * Adds an array of bytes to the buffer.
   *
   * @param src The bytes.
   * @return The number of bytes that have been written.
   */
  void put(byte[] src) {
    // Not the most efficient way, but it will do for now...
    for (byte b : src) {
      put(b);
    }
  }

  private void putInternal(byte b) {
    // Check for the horizontal edge
    if (xOffset == writableWidth) {
      // Check for the vertical edge
      if (yOffset == writableHeight) {
        // We've reached the end of the writable area
        throw new IndexOutOfBoundsException("The writable area has been exceeded.");
      }

      // Skip the next "margin" bytes
      byteBuffer.position(byteBuffer.position() + marginWidth);
      // Reset the horizontal offset
      xOffset = 0;
      // Increment the vertical offset
      yOffset++;
    }

    // Inxrement the horizontal offset
    xOffset++;
    // Write the byte
    byteBuffer.put(b);
  }

  ByteBuffer rewind() {
    byteBuffer.rewind();
    xOffset = 0;
    yOffset = 0;

    return byteBuffer;
  }

  void release() {
    factory.release(byteBuffer);
  }
}
