package slickng.gfx;

import java.util.HashMap;
import java.util.Map;
import slickng.Surface;

/**
 * A library for {@link Surface}s.
 */
public class SurfaceLibrary {

  private final Map<String, Surface> surfaces = new HashMap<>(64);

  /**
   * Retrieves a {@link Surface}.
   *
   * @param reference The reference.
   * @return The {@link Surface} or {@code null} if no {@link Surface} exists
   *         for the provided reference.
   */
  public Surface get(String reference) {
    return surfaces.get(reference);
  }

  /**
   * Adds a {@link Surface}.
   *
   * @param reference The reference.
   * @param surface   The {@link Surface}.
   * @return The {@link Surface} that was added.
   */
  public Surface add(String reference, Surface surface) {
    return surfaces.put(reference, surface);
  }

  /**
   * Removes a {@link Surface}.
   *
   * @param reference The reference.
   * @return The {@link Surface} or {@code null} if no {@link Surface} exists
   *         for the provided reference.
   */
  public Surface remove(String reference) {
    return surfaces.remove(reference);
  }
}
