package slickng;

/**
 * Context for a {@link Game} to interact with the {@link GameContainer}.
 */
public interface GameContext {

  /**
   * Retrieves the {@link Input}.
   *
   * @return The {@link Input}
   */
  Input getInput();

  /**
   * Retrieves the {@link Graphics}.
   *
   * @return The {@link Graphics}.
   */
  Graphics getGraphics();
}
