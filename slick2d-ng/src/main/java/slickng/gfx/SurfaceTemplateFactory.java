package slickng.gfx;

import slickng.Lease;
import slickng.UnsupportedFormatException;

/**
 * A factory for {@link SurfaceTemplate} instances.
 * <p>
 * This allows the {@link Graphics} implementation to specify dimensions for the
 * underlying data structures without requiring unnecessary copying of data
 * buffers.
 */
public interface SurfaceTemplateFactory {

  /**
   * Creates a new {@link SurfaceTemplate}.
   * <p>
   * This method returns an {@link SurfaceTemplate} with at least the width and height
   * as requested. Note that the resulting {@link SurfaceTemplate} is <b>not</b>
   * guaranteed to be of the requested width and height. This means that caller
   * must use {@link SurfaceTemplate#getTextureWidth()},
   * {@link SurfaceTemplate#getTextureHeight()} to determine how to write data to the
   * buffer.
   *
   * @param <T>    The type of {@link SurfaceTemplate}.
   * @param type   The type of {@link SurfaceTemplate}.
   * @param width  The minimal width.
   * @param height The minimal height.
   * @return A {@link Lease} on the {@link SurfaceTemplate}.
   * @throws UnsupportedFormatException If the {@link SurfaceTemplate} is not
   *                                    supported by the
   *                                    {@link SurfaceTemplateFactory}.
   */
  Lease<SurfaceTemplate> create(PixelFormat pixelFormat, int width, int height) throws UnsupportedFormatException;
}
