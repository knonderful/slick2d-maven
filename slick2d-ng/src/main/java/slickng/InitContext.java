package slickng;

import java.io.IOException;
import java.io.InputStream;
import slickng.gfx.ImageDataFactory;
import slickng.gfx.Surface;
import slickng.gfx.SurfaceFactory;
import slickng.gfx.ImageDataReader;

/**
 * The context for {@link Game#init(slickng.InitContext)}.
 */
public interface InitContext {

  /**
   * Creates a {@link Surface}.
   * <p>
   * This method will attempt to convert the {@link ImageData} from the
   * {@link ImageDataReader} to an appropriate format for the underlying
   * {@link SurfaceFactory}, if necessary. If conversion is necessary but not
   * possible, an {@link UnsupportedFormatException} will be thrown.
   *
   * @param reader      The {@link ImageDataReader}.
   * @param inputStream The {@link InputStream} that contains the image data.
   * @return The {@link Surface}.
   * @throws IOException                If an I/O exception occurred.
   * @throws UnsupportedFormatException If the {@link ImageData} from the
   *                                    {@link ImageDataReader} could not be
   *                                    converted to a fitting format.
   */
  Surface createSurface(ImageDataReader reader, InputStream inputStream) throws IOException, UnsupportedFormatException;

  /**
   * Retrieves the {@link ImageDataFactory}.
   *
   * @return The {@link ImageDataFactory}.
   */
  ImageDataFactory getImageDataFactory();

  /**
   * Retrieves the {@link SurfaceFactory}.
   *
   * @return The {@link SurfaceFactory}.
   */
  SurfaceFactory getSurfaceFactory();

  /**
   * Gets the {@link Input}.
   *
   * @return The {@link Input}.
   */
  Input getInput();
}
