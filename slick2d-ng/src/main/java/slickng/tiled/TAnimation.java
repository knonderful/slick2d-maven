package slickng.tiled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TAnimation {
  private final List<TAnimationFrame> frames;

  public TAnimation(List<TAnimationFrame> frames) {
    this.frames = new ArrayList<>(frames);
  }

  public List<TAnimationFrame> getFrames() {
    return Collections.unmodifiableList(frames);
  }
}
