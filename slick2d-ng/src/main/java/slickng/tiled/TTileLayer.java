package slickng.tiled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A tile layer in a {@link TMap}.
 */
public class TTileLayer implements TLayer {

  private final String name;
  private final int width;
  private final int height;
  private final List<TTile> tiles;

  /**
   * Creates a new instance.
   *
   * @param name   The name.
   * @param width  The width in tiles.
   * @param height The height in tiles.
   * @param tiles  The tiles.
   */
  public TTileLayer(String name, int width, int height, List<TTile> tiles) {
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
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (this.width != other.width) {
      return false;
    }
    if (this.height != other.height) {
      return false;
    }
    return Objects.equals(this.tiles, other.tiles);
  }

  /**
   * Retrieves the height.
   *
   * @return The height in tiles.
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
  public List<TTile> getTiles() {
    return Collections.unmodifiableList(tiles);
  }

  /**
   * Retrieves the width.
   *
   * @return The width in tiles.
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
