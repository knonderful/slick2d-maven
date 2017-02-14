package slickng.gfx;

import slickng.Color;

import static java.util.Objects.requireNonNull;

public class Rgba8Writer {

  private final ImageBuffer target;

  public Rgba8Writer(ImageBuffer target) {
    this.target = requireNonNull(target, "Argument target must be non-null.");
  }

  public void writePixel(Color color) {
    target.writeByte(color.getRed());
    target.writeByte(color.getGreen());
    target.writeByte(color.getBlue());
    target.writeByte(color.getAlpha());
  }
}
