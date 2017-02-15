package slickng.gfx;

import static java.util.Objects.requireNonNull;

public class Rgba8Writer implements ImageBufferWriter<Rgba8Pixel> {

  private final ImageBuffer target;

  public Rgba8Writer(ImageBuffer target) {
    this.target = requireNonNull(target, "Argument target must be non-null.");
  }

  @Override
  public void write(Rgba8Pixel color) {
    target.writeByte(color.getRed());
    target.writeByte(color.getGreen());
    target.writeByte(color.getBlue());
    target.writeByte(color.getAlpha());
  }
}
