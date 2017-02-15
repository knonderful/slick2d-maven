package slickng.gfx;

import static java.util.Objects.requireNonNull;

public class Indexed8Writer implements ImageBufferWriter<Byte> {

  private final ImageBuffer target;

  public Indexed8Writer(ImageBuffer target) {
    this.target = requireNonNull(target, "Argument target must be non-null.");
  }

  @Override
  public void write(Byte index) {
    target.writeByte(index);
  }
}
