package slickng;

/**
 * This class represent a game implementation.
 * <p>
 * When the game is started, {@link #init(slickng.GameContext)} is called. Then,
 * for each frame {@link #update(slickng.GameContext, int)} is called, followed
 * by {@link #render(slickng.GameContext, slickng.Graphics)}.
 * <p>
 * Finally, {@link #requestClose()} is called when the game is closed by the
 * user.
 */
public interface Game {

  /**
   * Initializes the game.
   *
   * @param context The game context.
   * @throws SlickException If the game could not be initialized.
   */
  public void init(GameContext context) throws SlickException;

  /**
   * Updates the game state.
   *
   * @param context The game context.
   * @param delta   The time since the last game update in milliseconds.
   * @throws SlickException If the game could not be updated.
   */
  public void update(GameContext context, int delta) throws SlickException;

  /**
   * Renders the game.
   *
   * @param context The render context.
   * @throws SlickException
   */
  public void render(RenderContext context) throws SlickException;

  /**
   * Requests to close the game.
   *
   * @return {@code true} if the game can be closed, otherwise {@code false}.
   */
  public boolean requestClose();
}
