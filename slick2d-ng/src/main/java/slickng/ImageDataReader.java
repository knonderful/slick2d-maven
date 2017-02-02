package slickng;

import java.io.IOException;
import java.io.InputStream;
import slickng.ImageData;

/**
 * A reader for image data.
 */
public interface ImageDataReader {

  /**
   * Reads the image data.
   *
   * @param inputStream The source input stream.
   * @return The image data.
   * @throws IOException If the provided input stream does not provide valid
   *                     image data.
   */
  ImageData read(InputStream inputStream) throws IOException;
}
