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

  private final GuardedByteBuffer data;
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
    this.pixelFormat = requireNonNull(pixelFormat, "Argument pixelFormat must be non-null.");
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    this.surfaceWidth = nextPowerOfTwo(imageWidth);
    this.surfaceHeight = nextPowerOfTwo(imageHeight);
    this.data = new GuardedByteBuffer(bufferFactory, this.imageWidth, this.imageHeight, this.surfaceWidth, this.surfaceHeight, this.pixelFormat.getBytesPerPixel());
  }

  @Override
  public byte readByte() {
    return data.get();
  }

  @Override
  public void rewind() {
    data.rewind();
  }

  @Override
  public void writeByte(int value) {
    writeByte((byte) (value & 0xFF));
  }

  @Override
  public void writeByte(byte value) {
    data.put(value);
  }

  ByteBuffer rewindAndGetData() {
    return data.rewind();
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
    // The image data
    byte[] imgData = new byte[imageWidth * imageHeight * pixelFormat.getBytesPerPixel()];
    int readInLine = 0;
      // Read until we have a complete line
      while (readInLine < imgData.length) {
      int lastRead = inputStream.read(imgData, readInLine, imgData.length - readInLine);
        if (lastRead < 0) {
          throw new IOException("Reached end of input stream, but expected more data.");
        }
        readInLine += lastRead;
      }

    // Write the data
    data.put(imgData);
  }

  void release() {
    data.release();
  }

  private static int nextPowerOfTwo(int value) {
    int powOfTwo = 2;

    while (powOfTwo < value) {
      powOfTwo *= 2;
    }

    return powOfTwo;
  }
}
