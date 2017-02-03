package slickng;

/**
 * The context for {@link Game#init(slickng.InitContext)}.
 */
public interface InitContext {

  /**
   * Gets the {@link Input}.
   *
   * @return The {@link Input}.
   */
  Input getInput();
}
