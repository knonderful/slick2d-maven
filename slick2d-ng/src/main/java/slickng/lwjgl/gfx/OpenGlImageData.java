package slickng.lwjgl.gfx;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import slickng.gfx.ImageData;
import slickng.gfx.PixelFormat;

/**
 * {@link ImageData} implementation for the OpenGL renderer.
 */
class OpenGlImageData implements ImageData {

  private final ByteBuffer data;
  private final PixelFormat pixelFormat;
  private final int width;
  private final int height;
  private final int textureWidth;
  private final int textureHeight;

  OpenGlImageData(PixelFormat pixelFormat, int width, int height) {
    this.width = width;
    this.height = height;
    this.textureWidth = nextPowerOfTwo(width);
    this.textureHeight = nextPowerOfTwo(height);
    this.pixelFormat = pixelFormat;
    this.data = createBuffer(this.textureWidth * this.textureHeight * this.pixelFormat.getBytesPerPixel());
  }

  @Override
  public ByteBuffer getData() {
    return data;
  }

  @Override
  public int getImageHeight() {
    return height;
  }

  @Override
  public PixelFormat getPixelFormat() {
    return pixelFormat;
  }

  @Override
  public int getTextureHeight() {
    return textureHeight;
  }

  @Override
  public int getTextureWidth() {
    return textureWidth;
  }

  @Override
  public int getImageWidth() {
    return width;
  }

  private static int nextPowerOfTwo(int value) {
    int powOfTwo = 2;

    while (powOfTwo < value) {
      powOfTwo *= 2;
    }

    return powOfTwo;
  }

  private static ByteBuffer createBuffer(int size) {
    ByteBuffer buffer = ByteBuffer.allocateDirect(size);
    // Use native byte order for passing into OpenGL
    buffer.order(ByteOrder.nativeOrder());
    return buffer;
  }
}
