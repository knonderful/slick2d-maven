package org.newdawn.slick.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.newdawn.slick.Input;
import org.newdawn.slick.util.InputAdapter;

import static java.util.Objects.requireNonNull;

/**
 * A {@link CommandProvider} that registers a listener with the provided
 * {@link Input} and keeps track of all the events that the {@link Input}
 * provides.
 * <p>
 * Note that the tracker commands should be explicitly cleared after every frame
 * by calling {@link #clearCommands()}.
 *
 * @param <T> The type of command.
 */
public class CommandTracker<T extends Command> implements CommandProvider<T> {

  private final Map<Control, T> commands = new HashMap<>();
  private final Set<T> pressCommands = new HashSet<>(32);
  private final Set<T> releaseCommands = new HashSet<>(32);

  /**
   * Creates a new {@link CommandHandler} with the provided {@link Input}.
   *
   * @param input The {@link Input}.
   */
  public CommandTracker(Input input) {
    requireNonNull(input, "Argument input must be non-null.")
            .addListener(new InputListenerImpl(this::handleRelease, this::handlePress));
  }

  @Override
  public Collection<T> getPressCommands() {
    return Collections.unmodifiableSet(pressCommands);
  }

  @Override
  public Collection<T> getReleaseCommands() {
    return Collections.unmodifiableSet(releaseCommands);
  }

  /**
   * Registers a control with its target command.
   *
   * @param control The control.
   * @param command The command.
   */
  public void register(Control control, T command) {
    commands.put(control, command);
  }

  /**
   * Unregisters a control.
   *
   * @param control The control.
   */
  public void unregister(Control control) {
    commands.remove(control);
  }

  private void handlePress(Control control) {
    T command = commands.get(control);
    if (command == null) {
      return;
    }

    pressCommands.add(command);
  }

  private void handleRelease(Control control) {
    T command = commands.get(control);
    if (command == null) {
      return;
    }

    releaseCommands.add(command);
  }

  /**
   * Clears the current commands.
   * <p>
   * This method should be called every frame after the commands have been
   * consumed.
   */
  public void clearCommands() {
    pressCommands.clear();
    releaseCommands.clear();
  }

  /**
   * A simple listener to respond to input and look up any required commands
   *
   * @author kevin
   */
  private static class InputListenerImpl extends InputAdapter {

    private final Consumer<Control> releaseConsumer;
    private final Consumer<Control> pressConsumer;

    public InputListenerImpl(Consumer<Control> releaseConsumer, Consumer<Control> pressConsumer) {
      this.releaseConsumer = requireNonNull(releaseConsumer, "Argument releaseConsumer must be non-null.");
      this.pressConsumer = requireNonNull(pressConsumer, "Argument pressConsumer must be non-null.");
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#isAcceptingInput()
     */
    @Override
    public boolean isAcceptingInput() {
      return true;
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#keyPressed(int, char)
     */
    @Override
    public void keyPressed(int key, char c) {
      pressConsumer.accept(new KeyControl(key));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#keyReleased(int, char)
     */
    @Override
    public void keyReleased(int key, char c) {
      releaseConsumer.accept(new KeyControl(key));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#mousePressed(int, int, int)
     */
    @Override
    public void mousePressed(int button, int x, int y) {
      pressConsumer.accept(new MouseButtonControl(button));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#mouseReleased(int, int, int)
     */
    @Override
    public void mouseReleased(int button, int x, int y) {
      releaseConsumer.accept(new MouseButtonControl(button));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#controllerLeftPressed(int)
     */
    @Override
    public void controllerLeftPressed(int controller) {
      pressConsumer.accept(new ControllerDirectionControl(controller, ControllerDirectionControl.LEFT));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#controllerLeftReleased(int)
     */
    @Override
    public void controllerLeftReleased(int controller) {
      releaseConsumer.accept(new ControllerDirectionControl(controller, ControllerDirectionControl.LEFT));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#controllerRightPressed(int)
     */
    @Override
    public void controllerRightPressed(int controller) {
      pressConsumer.accept(new ControllerDirectionControl(controller, ControllerDirectionControl.RIGHT));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#controllerRightReleased(int)
     */
    @Override
    public void controllerRightReleased(int controller) {
      releaseConsumer.accept(new ControllerDirectionControl(controller, ControllerDirectionControl.RIGHT));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#controllerUpPressed(int)
     */
    @Override
    public void controllerUpPressed(int controller) {
      pressConsumer.accept(new ControllerDirectionControl(controller, ControllerDirectionControl.UP));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#controllerUpReleased(int)
     */
    @Override
    public void controllerUpReleased(int controller) {
      releaseConsumer.accept(new ControllerDirectionControl(controller, ControllerDirectionControl.UP));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#controllerDownPressed(int)
     */
    @Override
    public void controllerDownPressed(int controller) {
      pressConsumer.accept(new ControllerDirectionControl(controller, ControllerDirectionControl.DOWN));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#controllerDownReleased(int)
     */
    @Override
    public void controllerDownReleased(int controller) {
      releaseConsumer.accept(new ControllerDirectionControl(controller, ControllerDirectionControl.DOWN));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#controllerButtonPressed(int,
     * int)
     */
    @Override
    public void controllerButtonPressed(int controller, int button) {
      pressConsumer.accept(new ControllerButtonControl(controller, button));
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#controllerButtonReleased(int,
     * int)
     */
    @Override
    public void controllerButtonReleased(int controller, int button) {
      releaseConsumer.accept(new ControllerButtonControl(controller, button));
    }
  };

}
