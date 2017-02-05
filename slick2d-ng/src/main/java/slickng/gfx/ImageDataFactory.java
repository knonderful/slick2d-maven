package slickng.gfx;

/**
 * A factory for {@link ImageData} instances.
 * <p>
 * This allows the {@link Graphics} implementation to specify dimensions for the
 * underlying data structures without requiring unnecessary copying of data
 * buffers.
 */
public interface ImageDataFactory {

  /**
   * Creates a new {@link ImageData}.
   * <p>
   * This method returns an {@link ImageData} with at least the width and height
   * as requested. Note that the resulting {@link ImageData} is <b>not</b>
   * guaranteed to be of the requested width and height. This means that caller
   * must use {@link ImageData#getTextureWidth()},
   * {@link ImageData#getTextureHeight()} and {@link ImageData#getPixelFormat()}
   * to determine how to write data to the buffer.
   *
   * @param pixelFormat The pixel format.
   * @param width  The minimal width.
   * @param height The minimal height.
   * @return The {@link ImageData}.
   */
  ImageData create(PixelFormat pixelFormat, int width, int height);
}
