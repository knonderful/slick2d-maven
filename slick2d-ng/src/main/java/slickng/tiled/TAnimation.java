package slickng.tiled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    final TAnimation other = (TAnimation) obj;
    return Objects.equals(this.frames, other.frames);
  }

  /**
   * Retrieves the frames.
   *
   * @return The frames.
   */
  public List<TAnimationFrame> getFrames() {
    return Collections.unmodifiableList(frames);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 17 * hash + Objects.hashCode(this.frames);
    return hash;
  }
}
