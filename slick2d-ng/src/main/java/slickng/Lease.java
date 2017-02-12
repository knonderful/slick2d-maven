package slickng;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A {@link Lease} represents a contract between the supplier and the consumer
 * of a subject.
 *
 * @param <T> The type of the subject of the lease.
 */
public interface Lease<T> {

  /**
   * Consumes the subject. The {@link Lease} will be expired immediately after
   * the consumer has been called.
   * <p>
   * The subject may <b>not</b> be referenced outside the scope of the consumer.
   * This would violate the lease contract. Doing so will result in undefined
   * behavior the next time the subject is accessed.
   *
   * @param consumer The consumer.
   * @throws IllegalStateException If this method is called after the subject
   *                               was released.
   */
  void consumeAndExpire(Consumer<T> consumer);

  /**
   * Consumes the subject, applies it to the provided function and returns the
   * result of said function. The {@link Lease} will be expired immediately
   * after the provided function has been called.
   * <p>
   * The subject may <b>not</b> be referenced outside the scope of the consumer.
   * This would violate the lease contract. Doing so will result in undefined
   * behavior the next time the subject is accessed.
   *
   * @param <U>      The result type of the function.
   * @param function The consumer.
   * @return The result of the function.
   * @throws IllegalStateException If this method is called after the subject
   *                               was released.
   */
  <U> U applyAndExpire(Function<T, U> function);

  /**
   * Borrows the subject.
   * <p>
   * The subject <b>must</b> be returned in the same game life cycle phase by
   * calling {@link #returnSubject()}.
   *
   * @return The subject.
   */
  T borrowSubject();

  /**
   * Returns the subject.
   *
   * @param subject The subject that was previously obtained by
   *                {@link #borrowSubject()}.
   */
  void returnSubject(T subject);

  /**
   * Expires the {@link Lease}.
   * <p>
   * After this method has been called, the subject is voided. Any access to the
   * subject after this point violates the lease contract and will result in
   * undefined behavior.
   */
  void expire();
}
