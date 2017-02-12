package slickng;

import java.util.function.Consumer;

/**
 * Utility class for constructing {@link Lease}s.
 */
public class Leases {

  /**
   * Creates a new {@link Lease}.
   *
   * @param <T>      The type of the subject.
   * @param subject  The subject.
   * @param releaser The release call-back handler.
   * @return The {@link Lease}.
   */
  public static <T> Lease<T> createLease(T subject, Consumer<T> releaser) {
    return new DefaultLease<>(subject, releaser);
  }

  private Leases() {
  }
}
