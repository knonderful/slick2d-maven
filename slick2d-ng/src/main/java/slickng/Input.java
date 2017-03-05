package slickng;

/**
 * {@link Input} is the entry point for all input-related actions.
 */
public interface Input {

  /**
   * Retrieves the keyboard input.
   *
   * @return The {@link Keyboard}.
   */
  Keyboard getKeyboard();

  /**
   * Polls the input devices for events.
   * <p>
   * This method should normally be called every frame.
   */
  void poll();
}
