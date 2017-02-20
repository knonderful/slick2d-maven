package slickng.tiled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TTile {
  private final int id;
  private final Map<String, String> properties;
  private final TAnimation animation;

  public TTile(int id, Map<String, String> properties, TAnimation animation) {
    this.id = id;
    this.properties = new HashMap<>(properties);
    this.animation = animation;
  }

  public TTile(int id) {
    this(id, Collections.emptyMap(), null);
  }

  public TAnimation getAnimation() {
    return animation;
  }

  public int getId() {
    return id;
  }

  public Map<String, String> getProperties() {
    return Collections.unmodifiableMap(properties);
  }
}
