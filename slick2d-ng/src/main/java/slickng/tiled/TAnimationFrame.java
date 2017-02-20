package slickng.tiled;

/**
 * A frame in a {@link TAnimation}.
 */
public class TAnimationFrame {

  private final int tileGid;
  private final int duration;

  /**
   * Creates a new frame.
   *
   * @param tileGid  The tile global ID.
   * @param duration The duration.
   */
  public TAnimationFrame(int tileGid, int duration) {
    this.tileGid = tileGid;
    this.duration = duration;
  }

  /**
   * Retrieves the duration.
   *
   * @return The duration.
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Retrieves the tile global ID.
   *
   * @return The tile global ID.
   */
  public int getTileGid() {
    return tileGid;
  }
}
