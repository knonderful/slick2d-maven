package slickng;

/**
 * A {@link GameContainer} contains the main game logic. It is responsible for
 * setting up the environment for a {@link Game} implementation (for instance
 * the renderer, input handling and audio processor).
 */
public interface GameContainer {

  /**
   * Starts the game.
   *
   * @throws SlickException If the game could not be started.
   */
  void start() throws SlickException;

  /**
   * Stops the game.
   *
   * @throws SlickException If the game could not be stopped.
   */
  void stop() throws SlickException;
}
