package slickng;

import java.util.function.Consumer;

/**
 * A {@link Consumer}-like interface that supports throwing a
 * {@link SlickException}.
 *
 * @param <T> The subject type.
 */
public interface SlickConsumer<T> {

  /**
   * Passes a value to the consumer.
   *
   * @param value The value.
   * @throws SlickException If the call caused an exception.
   */
  void accept(T value) throws SlickException;
}
