package slickng.lwjgl.gfx;

/**
 * Instantiation options for an {@link OpenGlGraphics} instance.
 */
public class OpenGlGraphicsOptions {

  private int displayWidth = 640;
  private int displayHeight = 480;
  private boolean fullscreen = false;
  private int fpsLimit = 0;

  private OpenGlGraphicsOptions() {
  }

  /**
   * Creates the default options.
   *
   * @return The options.
   */
  public static OpenGlGraphicsOptions getDefault() {
    return new OpenGlGraphicsOptions();
  }

  /**
   * Retrieves the FPS limit. A value of zero or less than zero indicates that
   * no FPS limit should be applied.
   *
   * @return The FPS limit.
   */
  public int getFpsLimit() {
    return fpsLimit;
  }

  /**
   * Sets the FPS limit.
   *
   * @see #getFpsLimit()
   * @param fpsLimit The FPS limit.
   * @return The {@link OpenGlGraphicsOptions}
   */
  public OpenGlGraphicsOptions setFpsLimit(int fpsLimit) {
    this.fpsLimit = fpsLimit;
    return this;
  }

  /**
   * Retrieves the height of the display.
   *
   * @return The height in pixels.
   */
  public int getDisplayHeight() {
    return displayHeight;
  }

  /**
   * Sets the display height.
   *
   * @see #getDisplayHeight()
   * @param height The height in pixels.
   * @return The {@link OpenGlGraphicsOptions}
   */
  public OpenGlGraphicsOptions setDisplayHeight(int height) {
    this.displayHeight = height;
    return this;
  }

  /**
   * Retrieves the width of the display.
   *
   * @return The width in pixels.
   */
  public int getDisplayWidth() {
    return displayWidth;
  }

  /**
   * Sets the display width.
   *
   * @see #getDisplayWidth()
   * @param width The width in pixels.
   * @return The {@link OpenGlGraphicsOptions}
   */
  public OpenGlGraphicsOptions setDisplayWidth(int width) {
    this.displayWidth = width;
    return this;
  }

  /**
   * Retrieves the flag that specifies whether the display should be run in
   * full-screen mode.
   *
   * @return {@code true} if full-screen is to be used, otherwise {@code false}.
   */
  public boolean isFullscreen() {
    return fullscreen;
  }

  /**
   * Sets the full-screen flag.
   *
   * @see #isFullscreen()
   * @param fullscreen {@code true} if full-screen is to be used, otherwise
   *                   {@code false}.
   * @return The {@link OpenGlGraphicsOptions}
   */
  public OpenGlGraphicsOptions setFullscreen(boolean fullscreen) {
    this.fullscreen = fullscreen;
    return this;
  }
}
