package slickng.tiled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class TTileLayer implements TLayer {

  private final String name;
  private final int width;
  private final int height;
  private final Collection<Integer> tiles;

  public TTileLayer(String name, int width, int height, Collection<Integer> tiles) {
    this.name = name;
    this.width = width;
    this.height = height;
    this.tiles = new ArrayList<>(tiles);
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
    final TTileLayer other = (TTileLayer) obj;
    if (this.width != other.width) {
      return false;
    }
    if (this.height != other.height) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    return Objects.equals(this.tiles, other.tiles);
  }

  public int getHeight() {
    return height;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * Retrieves GIDs of the tiles.
   *
   * @return A collection of GIDs.
   */
  public Collection<Integer> getTiles() {
    return Collections.unmodifiableCollection(tiles);
  }

  public int getWidth() {
    return width;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.name);
    hash = 97 * hash + this.width;
    hash = 97 * hash + this.height;
    hash = 97 * hash + Objects.hashCode(this.tiles);
    return hash;
  }

}
