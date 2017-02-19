package slickng.gfx;

/**
 * A pixel format specifies how the raw data of a {@link Surface} will be
 * interpreted for generating graphics.
 */
public enum PixelFormat {
  /**
   * RGBA with 8 bits per pixel.
   */
  RGBA_8(4, false),
  /**
   * Indexed with 8 bits per pixel.
   */
  INDEXED_8(1, true);

  private final int bytesPerPixel;
  private final int bitsPerPixel;
  private final boolean indexed;

  private PixelFormat(int bytesPerPixel, boolean indexed) {
    this.bytesPerPixel = bytesPerPixel;
    this.bitsPerPixel = bytesPerPixel * 8;
    this.indexed = indexed;
  }

  /**
   * Retrieves the total number of used bits per pixel.
   * <p>
   * This number is no larger than the return value from
   * {@link #getBytesPerPixel()} multiplied by 8.
   *
   * @return The number of bits per pixel.
   */
  public int getBitsPerPixel() {
    return bitsPerPixel;
  }

  /**
   * Retrieves the total number of bytes that is required to store a pixel.
   *
   * @return The number of bytes per pixel.
   */
  public int getBytesPerPixel() {
    return bytesPerPixel;
  }

  /**
   * Determines whether this is an indexed pixel format, i.e. if the pixels are
   * to be interpreted as indices into another surface.
   *
   * @return {@code true} if this {@link PixelFormat} is indexed, otherwise
   *         {@code false}.
   */
  public boolean isIndexed() {
    return indexed;
  }
}
