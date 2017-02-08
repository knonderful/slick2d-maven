package slickng.gfx;

import java.io.IOException;
import java.io.InputStream;
import slickng.UnsupportedFormatException;

/**
 * A reader for image data.
 */
public interface ImageDataReader {

  /**
   * Reads the image data.
   *
   * @param imageDataFactory The {@link ImageDataFactory}.
   * @param inputStream      The source input stream.
   * @return The image data.
   * @throws IOException                If the provided input stream does not
   *                                    provide valid
   *                                    image data.
   * @throws UnsupportedFormatException If the {@link ImageDataFactory} does not
   *                                    support the {@link ImageData} type
   *                                    required by the {@link ImageDataReader}.
   */
  ImageData read(ImageDataFactory imageDataFactory, InputStream inputStream) throws IOException, UnsupportedFormatException;
}
