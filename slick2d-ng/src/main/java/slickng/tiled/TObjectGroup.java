package slickng.tiled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * A {@link TLayer} of {@link TObject}s.
 */
public class TObjectGroup implements TLayer {

  private final String name;
  private final Collection<TObject> objects;

  /**
   * Creates a new instance.
   *
   * @param name    The name.
   * @param objects The objects.
   */
  public TObjectGroup(String name, Collection<TObject> objects) {
    this.name = requireNonNull(name, "Argument name must be non-null.");
    this.objects = new ArrayList<>(objects);
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
    final TObjectGroup other = (TObjectGroup) obj;
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    return Objects.equals(this.objects, other.objects);
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * Retrieves the objects.
   *
   * @return A collection of objects.
   */
  public Collection<TObject> getObjects() {
    return Collections.unmodifiableCollection(objects);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.name);
    hash = 97 * hash + Objects.hashCode(this.objects);
    return hash;
  }
}
