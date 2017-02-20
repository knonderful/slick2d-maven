package slickng.tiled;

import static java.util.Objects.requireNonNull;

public class TImage {

  private final String source;
  private final int width;
  private final int height;

  public TImage(String source, int width, int height) {
    this.source = requireNonNull(source, "Argument source must be non-null.");
    this.width = width;
    this.height = height;
  }

  public int getHeight() {
    return height;
  }

  public String getSource() {
    return source;
  }

  public int getWidth() {
    return width;
  }
}
