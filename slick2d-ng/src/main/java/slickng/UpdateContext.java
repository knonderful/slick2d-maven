package slickng;

/**
 * The context for {@link Game#update(slickng.UpdateContext, int)}.
 */
public interface UpdateContext {

  /**
   * Gets the {@link Input}.
   *
   * @return The {@link Input}.
   */
  Input getInput();
}
