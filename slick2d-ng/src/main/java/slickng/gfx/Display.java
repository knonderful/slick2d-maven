package slickng.gfx;

/**
 * {@link Display} represents the container to which the game graphics are being
 * rendered.
 */
public interface Display {

  /**
   * Returns the width of the display canvas.
   *
   * @return The width in pixels.
   */
  int getWidth();

  /**
   * Returns the height of the display canvas.
   *
   * @return The height in pixels.
   */
  int getHeight();

  /**
   * Determines whether the display is currently active, i.e. whether it has the
   * focus.
   *
   * @return {@code true} if the display is active, otherwise {@code false}.
   */
  boolean isActive();

  /**
   * Determines whether the user attempted to close the display.
   *
   * @return {@code true} if the user has attempted to close the display,
   *         otherwise {@code false}.
   */
  boolean isCloseRequested();

  /**
   * Determines whether the display is currently visible.
   *
   * @return {@code true} if the display is visible, otherwise {@code false}.
   */
  boolean isVisible();

  /**
   * Updates the display with the current state. This should be called for every
   * frame (even if nothing is to be rendered), so that the display state can be
   * updated.
   */
  void update();
}
