package slickng;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * The default {@link FreeLease} implementation.
 *
 * @param <T> The type of the subject of the {@link Lease}.
 */
public class DefaultLease<T> implements Lease<T> {

  private static final Logger LOG = Logger.getLogger(DefaultLease.class.getName());
  private final Lock lock = new ReentrantLock();
  private final T subject;
  private final Consumer<T> releaser;
  private volatile boolean expired = false;
  private volatile int borrowCount;

  /**
   * Creates a new instance.
   *
   * @param subject  The subject.
   * @param releaser The call-back for releasing the subject.
   */
  public DefaultLease(T subject, Consumer<T> releaser) {
    this.subject = requireNonNull(subject, "Argument subject must be non-null.");
    this.releaser = requireNonNull(releaser, "Argument releaser must be non-null.");
  }

  @Override
  public T borrowSubject() {
    lock.lock();
    try {
      return borrowUnsafe();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void consumeAndExpire(Consumer<T> consumer) {
    lock.lock();
    try {
      T subj = borrowUnsafe();
      try {
        consumer.accept(subj);
      } finally {
        returnUnsafe(subj);
        expire();
      }
    } finally {
      lock.unlock();
    }
  }

  @Override
  public <U> U applyAndExpire(Function<T, U> consumer) {
    lock.lock();
    try {
      U out;
      T subj = borrowUnsafe();
      try {
        out = consumer.apply(subj);
      } finally {
        returnUnsafe(subj);
        expire();
      }
      return out;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void expire() {
    lock.lock();
    try {
      if (!expired) {
        /*
         * This means that someone was too eager in releasing the subject. This
         * indicates some programmatical error, but it's not severe enough to
         * throw an exception (an crash the program). But we shouldn't call the
         * releaser again.
         */
        LOG.log(Level.FINER, () -> String.format("The subject (%s) is being released, but has already been released.", subject));
        return;
      }

      if (borrowCount > 0) {
        LOG.log(Level.WARNING, String.format("Lease is being expired while not all borrowed references have been returned (%d).", borrowCount));
      }

      releaser.accept(subject);
      expired = true;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void returnSubject(T subject) {
    lock.lock();
    try {
      returnUnsafe(subject);
    } finally {
      lock.unlock();
    }
  }

  private T borrowUnsafe() throws IllegalStateException {
    if (expired) {
      throw new IllegalStateException("The subject has already been released.");
    }

    borrowCount++;

    return subject;
  }

  private void returnUnsafe(T subject) {
    borrowCount--;
  }
}
