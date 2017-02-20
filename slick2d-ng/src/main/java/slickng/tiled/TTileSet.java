package slickng.tiled;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class TTileSet {

  private final String name;
  private final int tileWidth;
  private final int tileHeight;
  private final int firstGid;
  private final int lastGid;
  private final TImage image;
  private final Map<Integer, TTile> tiles;

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
      out.put(tile.getId(), tile);
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
    if (this.firstGid != other.firstGid) {
      return false;
    }
    if (this.lastGid != other.lastGid) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(this.image, other.image)) {
      return false;
    }
    if (!Objects.equals(this.tiles, other.tiles)) {
      return false;
    }
    return true;
  }

  public int getFirstGid() {
    return firstGid;
  }

  public TImage getImage() {
    return image;
  }

  public int getLastGid() {
    return lastGid;
  }

  public String getName() {
    return name;
  }

  public TTile getTile(int gid) {
    return tiles.get(gid);
  }

  public int getTileHeight() {
    return tileHeight;
  }

  public int getTileWidth() {
    return tileWidth;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 79 * hash + Objects.hashCode(this.name);
    hash = 79 * hash + this.firstGid;
    hash = 79 * hash + this.lastGid;
    hash = 79 * hash + Objects.hashCode(this.image);
    hash = 79 * hash + Objects.hashCode(this.tiles);
    return hash;
  }
}
