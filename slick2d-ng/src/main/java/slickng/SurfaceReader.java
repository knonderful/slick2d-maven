package slickng;

import java.io.IOException;
import java.io.InputStream;

import static java.util.Objects.requireNonNull;

/**
 * A class that binds an {@link ImageDataReader} and a {@link SurfaceFactory}
 * for easy {@link Surface} loading.
 */
public class SurfaceReader {

  private final ImageDataReader reader;
  private final SurfaceFactory surfaceFactory;

  /**
   * Creates a new instance.
   *
   * @param reader         The {@link ImageDataReader}.
   * @param surfaceFactory The function to produce a {@link Surface} out of a
   *                       {@link ImageData}.
   */
  public SurfaceReader(ImageDataReader reader, SurfaceFactory surfaceFactory) {
    this.reader = requireNonNull(reader, "Argument reader must be non-null.");
    this.surfaceFactory = requireNonNull(surfaceFactory, "Argument surfaceFactory must be non-null.");
  }

  public Surface loadSurface(InputStream inputStream) throws IOException {
    return surfaceFactory.create(reader.read(inputStream));
  }
}
