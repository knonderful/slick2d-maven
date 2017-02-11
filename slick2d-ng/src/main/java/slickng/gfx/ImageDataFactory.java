package slickng.gfx;

import slickng.UnsupportedFormatException;

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
   *
   * @param <T>    The type of {@link ImageData}.
   * @param type   The type of {@link ImageData}.
   * @param width  The minimal width.
   * @param height The minimal height.
   * @return A {@link Lease} on the {@link ImageData}.
   * @throws UnsupportedFormatException If the {@link ImageData} is not
   *                                    supported by the
   *                                    {@link ImageDataFactory}.
   */
  <T extends ImageData> T create(Class<T> type, int width, int height) throws UnsupportedFormatException;

  /**
   * Releases a {@link ImageData} that was created using this
   * {@link ImageDataFactory}.
   *
   * @param imageData The {@link ImageData}.
   */
  void release(ImageData imageData);
}
