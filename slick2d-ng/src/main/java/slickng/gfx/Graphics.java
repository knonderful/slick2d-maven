package slickng.gfx;

import slickng.RenderContext;
import slickng.SlickException;

/**
 * {@link Graphics} represents the graphics sub-system. It contains the
 * framework for all graphics-based operations.
 */
public interface Graphics {

  /**
   * Initializes the {@link Graphics} and creates the {@link Display}.
   * <p>
   * This method should be called before any other method.
   *
   * @return The {@link Display}.
   * @throws SlickException If the {@link Graphics} could not be initialized.
   */
  Display init() throws SlickException;

  /**
   * De-initializes the {@link Graphics}.
   */
  void deinit();

  /**
   * Retrieves a {@link ImageDataFactory} for creating image data.
   *
   * @return The {@link ImageDataFactory}.
   */
  ImageDataFactory getImageDataFactory();

  /**
   * Signals the start of a render iteration.
   *
   * @return The {@link RenderContext}.
   */
  RenderContext startRender();

  /**
   * Signals the end of a render iteration.
   */
  void finishRender();
}
