package slickng;

/**
 * An exception that indicates that an unsupported format was provided.
 */
public class UnsupportedFormatException extends SlickException {

  private static final long serialVersionUID = 1L;

  public UnsupportedFormatException() {
  }

  public UnsupportedFormatException(String message) {
    super(message);
  }

  public UnsupportedFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnsupportedFormatException(Throwable cause) {
    super(cause);
  }

  public UnsupportedFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
