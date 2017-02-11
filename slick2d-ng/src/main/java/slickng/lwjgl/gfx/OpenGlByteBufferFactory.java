package slickng.lwjgl.gfx;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * A factory for {@link ByteBuffer}s.
 */
class OpenGlByteBufferFactory {

  OpenGlByteBufferFactory() {
  }

  /**
   * Creates a new {@link ByteBuffer}.
   *
   * @param size The capacity of the buffer.
   * @return The {@link ByteBuffer}.
   */
  ByteBuffer create(int size) {
    ByteBuffer buffer = ByteBuffer.allocateDirect(size);
    // Use native byte order for passing into OpenGL
    buffer.order(ByteOrder.nativeOrder());
    return buffer;
  }

  void release(ByteBuffer buffer) {
    // Do nothing
  }
}
