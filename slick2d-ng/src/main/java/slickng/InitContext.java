package slickng;

import slickng.gfx.ImageDataFactory;

/**
 * The context for {@link Game#init(slickng.InitContext)}.
 */
public interface InitContext {

  /**
   * Retrieves the {@link ImageDataFactory}.
   *
   * @return The {@link ImageDataFactory}.
   */
  ImageDataFactory getImageDataFactory();

  /**
   * Gets the {@link Input}.
   *
   * @return The {@link Input}.
   */
  Input getInput();
}
