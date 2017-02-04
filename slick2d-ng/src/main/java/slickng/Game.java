package slickng;

/**
 * This class represent a game implementation.
 * <p>
 * When the game is started, {@link #init(InitContext)} is called. Then,
 * for each frame {@link #update(UpdateContext, int)} is called, followed
 * by {@link #render(RenderContext)}.
 * <p>
 * {@link #requestClose()} is called when the game is closed by the
 * user.
 * <p>
 * {@link #deinit()} is called when the game loop has ended and the game will be
 * closed.
 */
public interface Game {

  /**
   * Initializes the game.
   *
   * @param context The init context.
   * @throws SlickException If the game could not be initialized.
   */
  void init(InitContext context) throws SlickException;

  /**
   * De-initializes the game.
   */
  void deinit();

  /**
   * Updates the game state.
   *
   * @param context The update context.
   * @param delta   The time since the last game update in milliseconds.
   * @throws SlickException If the game could not be updated.
   */
  void update(UpdateContext context, int delta) throws SlickException;

  /**
   * Renders the game.
   *
   * @param context The render context.
   * @throws SlickException
   */
  void render(RenderContext context) throws SlickException;

  /**
   * Requests to close the game.
   *
   * @return {@code true} if the game can be closed, otherwise {@code false}.
   */
  boolean requestClose();
}
