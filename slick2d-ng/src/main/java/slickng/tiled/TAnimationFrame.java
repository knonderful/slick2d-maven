package slickng.tiled;

public class TAnimationFrame {

  private final int tileId;
  private final int duration;

  public TAnimationFrame(int tileId, int duration) {
    this.tileId = tileId;
    this.duration = duration;
  }

  public int getDuration() {
    return duration;
  }

  public int getTileId() {
    return tileId;
  }
}
