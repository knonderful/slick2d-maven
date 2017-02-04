package slickng;

public interface Graphics {

  /**
   * Initializes the {@link Graphics}.
   *
   * @throws SlickException If the {@link Graphics} could not be initialized.
   */
  void init() throws SlickException;

  /**
   * De-initializes the {@link Graphics}.
   */
  void deinit();

  /**
   * Determines whether the display has the focus.
   *
   * @return {@code true} if the display has the focus, otherwise {@code false}.
   */
  boolean hasFocus();

  /**
   * Retrieves a {@link SurfaceFactory} for creating surfaces.
   *
   * @return The {@link SurfaceFactory}.
   */
  SurfaceFactory getSurfaceFactory();

  /**
   * Retrieves a {@link ImageDataFactory} for creating image data.
   *
   * @return The {@link ImageDataFactory}.
   */
  ImageDataFactory getImageDataFactory();

  /**
   * Returns the width of the graphics context.
   *
   * @return The width in pixels.
   */
  int getWidth();

  /**
   * Returns the height of the graphics context.
   *
   * @return The height in pixels.
   */
  int getHeight();

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

  /**
   * Determines whether the display has been requested to close by the user.
   *
   * @return {@code true} if the user requested to close the display, otherwise
   *         {@code false}.
   */
  boolean isCloseRequested();
}
