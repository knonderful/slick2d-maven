package org.newdawn.slick.command;

import java.util.Collection;

/**
 * Provides the commands that have been pressed and released.
 * @param <T> The type of command.
 */
public interface CommandProvider<T extends Command> {

  /**
   * Retrieves the commands that have been pressed.
   *
   * @return A collection of commands.
   */
  public Collection<T> getPressCommands();

  /**
   * Retrieves the commands that have been released.
   *
   * @return A collection of commands.
   */
  public Collection<T> getReleaseCommands();

}
