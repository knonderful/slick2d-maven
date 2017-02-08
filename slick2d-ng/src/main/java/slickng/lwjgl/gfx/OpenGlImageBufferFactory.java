package slickng.lwjgl.gfx;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import slickng.gfx.ByteBufferImageBuffer;
import slickng.gfx.ImageBuffer;
import slickng.gfx.ImageBufferFactory;

class OpenGlImageBufferFactory implements ImageBufferFactory {

  OpenGlImageBufferFactory() {
  }

  @Override
  public ImageBuffer create(int width, int height, int bytesPerPixel) {
    int surfaceWidth = nextPowerOfTwo(width);
    int surfaceHeight = nextPowerOfTwo(height);
    ByteBuffer buffer = createBuffer(surfaceWidth, surfaceHeight, bytesPerPixel);
    return new ByteBufferImageBuffer(buffer, bytesPerPixel, width, height, surfaceWidth, surfaceHeight);
  }

  private static int nextPowerOfTwo(int value) {
    int powOfTwo = 2;

    while (powOfTwo < value) {
      powOfTwo *= 2;
    }

    return powOfTwo;
  }

  private static ByteBuffer createBuffer(int surfaceWidth, int surfaceHeight, int bytesPerPixel) {
    ByteBuffer buffer = ByteBuffer.allocateDirect(surfaceWidth * surfaceHeight * bytesPerPixel);
    // Use native byte order for passing into OpenGL
    buffer.order(ByteOrder.nativeOrder());
    return buffer;
  }
}
