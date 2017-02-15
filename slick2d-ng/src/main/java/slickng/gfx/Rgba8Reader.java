package slickng.gfx;

import static java.util.Objects.requireNonNull;

public class Rgba8Reader implements ImageBufferReader<Rgba8Pixel> {

  private final ImageBuffer source;

  public Rgba8Reader(ImageBuffer source) {
    this.source = requireNonNull(source, "Argument source must be non-null.");
  }

  @Override
  public Rgba8Pixel read() {
    return new Rgba8Pixel(source.readByte(), source.readByte(), source.readByte(), source.readByte());
  }
}
