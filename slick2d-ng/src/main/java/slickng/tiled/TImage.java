package slickng.tiled;

import static java.util.Objects.requireNonNull;

/**
 * An image in a {@link TMap}.
 */
public class TImage {

  private final String source;
  private final int width;
  private final int height;

  /**
   * Creates a new instance.
   *
   * @param source The source. This string represents the location of the image.
   * @param width  The image width.
   * @param height The image height.
   */
  public TImage(String source, int width, int height) {
    this.source = requireNonNull(source, "Argument source must be non-null.");
    this.width = width;
    this.height = height;
  }

  /**
   * Retrieves the height.
   *
   * @return The height.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Retrieves the source. This string represents the location of the image.
   *
   * @return The source.
   */
  public String getSource() {
    return source;
  }

  /**
   * Retrieves the width.
   *
   * @return The width.
   */
  public int getWidth() {
    return width;
  }
}
