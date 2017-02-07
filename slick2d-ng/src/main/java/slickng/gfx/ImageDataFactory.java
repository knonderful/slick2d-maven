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
   * {@link ImageData#getTextureHeight()} to determine how to write data to the
   * buffer.
   * <p>
   * The obtained {@link ImageData} <b>must</b> be released by after use calling
   * {@link #release(slickng.gfx.ImageData)}.
   *
   * @param pixelFormat The pixel format.
   * @param width       The minimal width.
   * @param height      The minimal height.
   * @return The {@link ImageData}.
   */
  ImageData create(PixelFormat pixelFormat, int width, int height);

  /**
   * Releases a previously created {@link ImageData}.
   * <p>
   * This method <b>must</b> be called after the consumer does not want to use
   * the {@link ImageData} anymore. The {@link ImageData} should not be used
   * after it has been released.
   *
   * @param imageData The {@link ImageData}.
   */
  void release(ImageData imageData);
}
