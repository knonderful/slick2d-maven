package slickng;

import slickng.gfx.Surface;
import slickng.gfx.ImageDataReader;
import java.io.InputStream;

/**
 * The context for {@link Game#init(slickng.InitContext)}.
 */
public interface InitContext {

  /**
   * Creates a {@link Surface}.
   *
   * @param reader      The {@link ImageDataReader}.
   * @param inputStream The {@link InputStream} that contains the image data.
   */
  Surface createSurface(ImageDataReader reader, InputStream inputStream) throws SlickException;

  /**
   * Gets the {@link Input}.
   *
   * @return The {@link Input}.
   */
  Input getInput();
}
