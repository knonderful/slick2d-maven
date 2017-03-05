package slickng.lwjgl.input;

import slickng.Input;
import slickng.Keyboard;

/**
 * A LWJGL-based {@link slickng.Input} implementation.
 */
public class LwjglInput implements Input {

  private final KeyboardImpl keyboard = new KeyboardImpl();

  @Override
  public Keyboard getKeyboard() {
    return keyboard;
  }

  @Override
  public void poll() {
    keyboard.poll();
  }
}
