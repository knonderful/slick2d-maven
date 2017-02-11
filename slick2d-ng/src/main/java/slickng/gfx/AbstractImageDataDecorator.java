package slickng.gfx;

import static java.util.Objects.requireNonNull;

/**
 * Abstract class for {@link ImageBuffer} decorators.
 */
public abstract class AbstractImageDataDecorator implements ImageData {

  private final ImageData decorated;

  protected AbstractImageDataDecorator(ImageData decorated) {
    this.decorated = requireNonNull(decorated, "Argument decorated must be non-null.");
  }

  @Override
  public Surface createSurface() {
    return decorated.createSurface();
  }

  @Override
  public ImageBuffer getBuffer() {
    return decorated.getBuffer();
  }
}
