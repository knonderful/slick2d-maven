package slickng;

import java.util.Objects;

/**
 * The default {@link GameContext} implementation.
 */
public class DefaultGameContext implements GameContext {

  private final Graphics graphics;

  public DefaultGameContext(Graphics graphics) {
    this.graphics = Objects.requireNonNull(graphics, "Argument graphics must be non-null.");
  }

  @Override
  public Graphics getGraphics() {
    return graphics;
  }

  @Override
  public Input getInput() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
