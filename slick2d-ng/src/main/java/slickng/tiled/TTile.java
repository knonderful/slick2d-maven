package slickng.tiled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A tile in a {@link TMap}.
 */
public class TTile {

  private final int gid;
  private final Map<String, String> properties;
  private final TAnimation animation;

  /**
   * Creates a new instance.
   *
   * @param gid        The global ID.
   * @param properties The properties.
   * @param animation  The animation.
   */
  public TTile(int gid, Map<String, String> properties, TAnimation animation) {
    this.gid = gid;
    this.properties = new HashMap<>(properties);
    this.animation = animation;
  }

  /**
   * Creates a new instance.
   *
   * @param gid The global ID.
   */
  public TTile(int gid) {
    this(gid, Collections.emptyMap(), null);
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
    final TTile other = (TTile) obj;
    if (this.gid != other.gid) {
      return false;
    }
    if (!Objects.equals(this.animation, other.animation)) {
      return false;
    }
    return Objects.equals(this.properties, other.properties);
  }

  /**
   * Retrieves the animation.
   *
   * @return The animation.
   */
  public TAnimation getAnimation() {
    return animation;
  }

  /**
   * Retrieves the global ID.
   *
   * @return The global ID.
   */
  public int getGid() {
    return gid;
  }

  /**
   * Retrieves the properties.
   *
   * @return The properties.
   */
  public Map<String, String> getProperties() {
    return Collections.unmodifiableMap(properties);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + this.gid;
    hash = 97 * hash + Objects.hashCode(this.properties);
    hash = 97 * hash + Objects.hashCode(this.animation);
    return hash;
  }
}
