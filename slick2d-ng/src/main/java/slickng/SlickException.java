package slickng;

public class SlickException extends Exception {

  private static final long serialVersionUID = 1L;

  public SlickException() {
  }

  public SlickException(String message) {
    super(message);
  }

  public SlickException(String message, Throwable cause) {
    super(message, cause);
  }

  public SlickException(Throwable cause) {
    super(cause);
  }

  public SlickException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
