package slickng.tiled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An animation in a {@link TMap}.
 */
public class TAnimation {

  private final List<TAnimationFrame> frames;

  /**
   * Creates a new instance.
   *
   * @param frames The animation frames.
   */
  public TAnimation(List<TAnimationFrame> frames) {
    this.frames = new ArrayList<>(frames);
  }

  /**
   * Retrieves the frames.
   *
   * @return The frames.
   */
  public List<TAnimationFrame> getFrames() {
    return Collections.unmodifiableList(frames);
  }
}
