package slickng.tiled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * A tile layer in a {@link TMap}.
 */
public class TTileLayer implements TLayer {

  private final String name;
  private final int width;
  private final int height;
  private final Collection<TTile> tiles;

  /**
   * Creates a new instance.
   *
   * @param name   The name.
   * @param width  The width.
   * @param height The height.
   * @param tiles  The tiles.
   */
  public TTileLayer(String name, int width, int height, Collection<TTile> tiles) {
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

  /**
   * Retrieves the height.
   *
   * @return The height.
   */
  public int getHeight() {
    return height;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * Retrieves the tiles.
   *
   * @return A collection of tiles.
   */
  public Collection<TTile> getTiles() {
    return Collections.unmodifiableCollection(tiles);
  }

  /**
   * Retrieves the width.
   *
   * @return The width.
   */
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
