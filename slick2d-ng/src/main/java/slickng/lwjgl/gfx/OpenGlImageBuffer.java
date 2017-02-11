package slickng.lwjgl.gfx;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import slickng.gfx.ImageBuffer;
import slickng.gfx.PixelFormat;

import static java.util.Objects.requireNonNull;

/**
 * {@link ImageBuffer} implementation for the OpenGL renderer.
 */
public class OpenGlImageBuffer implements ImageBuffer {

  private final OpenGlByteBufferFactory bufferFactory;
  private final ByteBuffer data;
  private final PixelFormat pixelFormat;
  private final int imageWidth;
  private final int imageHeight;
  private final int surfaceWidth;
  private final int surfaceHeight;

  /**
   * Creates a new instance.
   *
   * @param data          The buffer for storing image data.
   * @param pixelFormat   The {@link PixelFormat}.
   * @param imageWidth    The image width.
   * @param imageHeight   The image height.
   * @param surfaceWidth  The surface width.
   * @param surfaceHeight The surface height.
   */
  OpenGlImageBuffer(OpenGlByteBufferFactory bufferFactory, PixelFormat pixelFormat, int imageWidth, int imageHeight) {
    this.bufferFactory = requireNonNull(bufferFactory, "Argument bufferFactory must be non-null.");
    this.pixelFormat = requireNonNull(pixelFormat, "Argument pixelFormat must be non-null.");
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    this.surfaceWidth = nextPowerOfTwo(imageWidth);
    this.surfaceHeight = nextPowerOfTwo(imageHeight);
    this.data = requireNonNull(
            createBuffer(bufferFactory, this.surfaceWidth, this.surfaceHeight, this.pixelFormat.getBytesPerPixel()),
            "Argument dataLease must be non-null.");
  }

  ByteBuffer getData() {
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
  public PixelFormat getPixelFormat() {
    return pixelFormat;
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
    int bytesPerLine = pixelFormat.getBytesPerPixel() * imageWidth;
    // Number of bytes to skip in the destination buffer after each line
    int skipPerLine = pixelFormat.getBytesPerPixel() * (surfaceWidth - imageWidth);

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

  void release() {
    bufferFactory.release(data);
  }

  private static int nextPowerOfTwo(int value) {
    int powOfTwo = 2;

    while (powOfTwo < value) {
      powOfTwo *= 2;
    }

    return powOfTwo;
  }

  private static ByteBuffer createBuffer(OpenGlByteBufferFactory bufferFactory, int width, int height, int bytesPerPixel) {
    return bufferFactory.create(width * height * bytesPerPixel);
  }
}
