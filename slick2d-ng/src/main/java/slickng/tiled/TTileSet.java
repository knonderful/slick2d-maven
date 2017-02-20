package slickng.tiled;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * A tile set for a {@link TMap}.
 */
public class TTileSet {

  private transient final int firstGid;
  private transient final int lastGid;
  private final String name;
  private final int tileWidth;
  private final int tileHeight;
  private final TImage image;
  private final Map<Integer, TTile> tiles;

  /**
   * Creates a new instance.
   *
   * @param name       The name of the tile set.
   * @param tileWidth  The tile width.
   * @param tileHeight The tile height.
   * @param image      The image.
   * @param tiles      The tiles.
   */
  public TTileSet(String name, int tileWidth, int tileHeight, TImage image, Collection<TTile> tiles) {
    this.tiles = groupById(tiles);
    this.tileWidth = tileWidth;
    this.tileHeight = tileHeight;
    this.name = name;
    this.firstGid = getFirst(this.tiles);
    this.lastGid = getLast(this.tiles);
    this.image = image;
  }

  private static Map<Integer, TTile> groupById(Collection<TTile> tiles) {
    Map<Integer, TTile> out = new TreeMap<>();
    tiles.forEach(tile -> {
      out.put(tile.getGid(), tile);
    });

    return out;
  }

  private static Integer getFirst(Map<Integer, TTile> tiles) {
    return tiles.keySet().stream()
            .findFirst()
            .orElse(null);
  }

  private static Integer getLast(Map<Integer, TTile> tiles) {
    Integer last = null;
    for (Integer id : tiles.keySet()) {
      last = id;
    }
    return last;
  }

  /**
   * Determines whether the tile with the provided global ID is contained within
   * this tile set.
   *
   * @param gid The global ID.
   * @return {@code true} if the tile is in this tile set, otherwise
   *         {@code false}.
   */
  public boolean containsTile(int gid) {
    return gid >= firstGid && gid <= lastGid;
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
    final TTileSet other = (TTileSet) obj;
    if (this.tileWidth != other.tileWidth) {
      return false;
    }
    if (this.tileHeight != other.tileHeight) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(this.image, other.image)) {
      return false;
    }
    return Objects.equals(this.tiles, other.tiles);
  }

  /**
   * Retrieves the image.
   *
   * @return The image.
   */
  public TImage getImage() {
    return image;
  }

  /**
   * Retrieves the name.
   *
   * @return The name.
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the tile with the provided global ID.
   *
   * @param gid The global ID.
   * @return The tile or {@code null} if the tile is not in this tile set.
   */
  public TTile getTile(int gid) {
    return tiles.get(gid);
  }

  /**
   * Retrieves the tile height.
   *
   * @return The tile height.
   */
  public int getTileHeight() {
    return tileHeight;
  }

  /**
   * Retrieves the tile width.
   *
   * @return The tile width.
   */
  public int getTileWidth() {
    return tileWidth;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 41 * hash + Objects.hashCode(this.name);
    hash = 41 * hash + this.tileWidth;
    hash = 41 * hash + this.tileHeight;
    hash = 41 * hash + Objects.hashCode(this.image);
    hash = 41 * hash + Objects.hashCode(this.tiles);
    return hash;
  }

}
