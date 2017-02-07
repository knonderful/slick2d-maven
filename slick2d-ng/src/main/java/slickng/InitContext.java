package slickng;

import java.io.InputStream;
import slickng.gfx.ImageDataReader;
import slickng.gfx.Surface;

/**
 * The context for {@link Game#init(slickng.InitContext)}.
 */
public interface InitContext {

  /**
   * Creates a {@link Surface}.
   *
   * @param reader      The {@link ImageDataReader}.
   * @param inputStream The {@link InputStream} that contains the image data.
   * @return The {@link Surface}.
   * @throws SlickException If the {@link Surface} could not be created.
   */
  Surface createSurface(ImageDataReader reader, InputStream inputStream) throws SlickException;

  /**
   * Gets the {@link Input}.
   *
   * @return The {@link Input}.
   */
  Input getInput();
}
