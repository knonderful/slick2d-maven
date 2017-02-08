package slickng.gfx;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static java.util.Objects.requireNonNull;

/**
 * {@link ImageBuffer} implementation based on {@link ByteBuffer}.
 */
public class ByteBufferImageBuffer implements ImageBuffer {

  private final ByteBuffer data;
  private final int bytesPerPixel;
  private final int imageWidth;
  private final int imageHeight;
  private final int surfaceWidth;
  private final int surfaceHeight;

  /**
   * Creates a new instance.
   *
   * @param data          The buffer for storing image data.
   * @param bytesPerPixel The number of bytes per pixel.
   * @param imageWidth    The image width.
   * @param imageHeight   The image height.
   * @param surfaceWidth  The surface width.
   * @param surfaceHeight The surface height.
   */
  public ByteBufferImageBuffer(ByteBuffer data, int bytesPerPixel, int imageWidth, int imageHeight, int surfaceWidth, int surfaceHeight) {
    this.data = requireNonNull(data, "Argument data must be non-null.");
    this.bytesPerPixel = bytesPerPixel;
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    this.surfaceWidth = surfaceWidth;
    this.surfaceHeight = surfaceHeight;
  }

  @Override
  public ByteBuffer getData() {
    return data;
  }

  @Override
  public int getImageHeight() {
    return imageHeight;
  }

  @Override
  public int getImageWidth() {
    return imageWidth;
  }

  @Override
  public int getSurfaceHeight() {
    return surfaceHeight;
  }

  @Override
  public int getSurfaceWidth() {
    return surfaceWidth;
  }

  @Override
  public void write(InputStream inputStream) throws IOException {
    // Number of bytes for image data per line
    int bytesPerLine = bytesPerPixel * imageWidth;
    // Number of bytes to skip in the destination buffer after each line
    int skipPerLine = bytesPerPixel * (surfaceWidth - imageWidth);

    // The current line
    byte[] line = new byte[bytesPerLine];
    for (int i = 0; i < imageHeight; i++) {
      // The number of bytes that have already been read for the current line
      int readInLine = 0;
      // Read until we have a complete line
      while (readInLine < bytesPerLine) {
        int lastRead = inputStream.read(line, readInLine, bytesPerLine - readInLine);
        if (lastRead < 0) {
          throw new IOException("Reached end of input stream, but expected more data.");
        }
        readInLine += lastRead;
      }

      // Write the line
      data.put(line);

      // Skip the "excess" surface space
      if (skipPerLine > 0) {
        data.position(data.position() + skipPerLine);
      }
    }

    // Note: do not flip() here, because we want to keep the limit at the capacity of the buffer
    data.rewind();
  }
}
