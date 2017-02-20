package slickng.tiled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
}
