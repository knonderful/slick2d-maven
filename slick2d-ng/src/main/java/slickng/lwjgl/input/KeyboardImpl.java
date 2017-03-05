package slickng.lwjgl.input;

import org.lwjgl.input.Keyboard;

/**
 * The LWJGL {@link slickng.Keyboard} implementation.
 */
class KeyboardImpl implements slickng.Keyboard {

  /**
   * Polls for all input events.
   */
  void poll() {
    while (Keyboard.next()) {
    }
  }

  @Override
  public boolean isPressed(int keyCode) {
    /*
     * The key codes for the slick-ng Keyboard interface map directly to LWJGL's
     * key codes, so no conversion is necessary.
     */
    return Keyboard.isKeyDown(keyCode);
  }

}
