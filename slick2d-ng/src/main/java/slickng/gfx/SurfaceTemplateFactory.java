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
   * This method returns a {@link SurfaceTemplate} with the provided image width
   * and height.
   *
   * @param pixelFormat The {@link PixelFormat}.
   * @param width       The image width.
   * @param height      The image height.
   * @return A {@link Lease} on the {@link SurfaceTemplate}.
   * @throws UnsupportedFormatException If the {@link SurfaceTemplate} is not
   *                                    supported by the
   *                                    {@link SurfaceTemplateFactory}.
   */
  Lease<SurfaceTemplate> create(PixelFormat pixelFormat, int width, int height) throws UnsupportedFormatException;
}
