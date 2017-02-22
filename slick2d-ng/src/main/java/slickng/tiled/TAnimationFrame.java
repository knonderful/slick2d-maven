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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final TAnimationFrame other = (TAnimationFrame) obj;
    if (this.tileGid != other.tileGid) {
      return false;
    }
    return this.duration == other.duration;
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

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + this.tileGid;
    hash = 37 * hash + this.duration;
    return hash;
  }
}
