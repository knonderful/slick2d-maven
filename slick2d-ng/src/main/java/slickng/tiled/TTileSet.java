package slickng.tiled;

import java.util.Objects;
import slickng.gfx.Surface;
import slickng.gfx.Tile;
import slickng.gfx.TileSheet;

public class TTileSet {

  private final String name;
  private final int firstGid;
  private final int lastGid;
  private final Surface surface;
  private final TileSheet tileSheet;

  public TTileSet(String name, int firstGid, Surface surface, TileSheet tileSheet) {
    this.name = name;
    this.firstGid = firstGid;
    this.lastGid = firstGid + tileSheet.getWidth() * tileSheet.getHeight() - 1;
    this.surface = surface;
    this.tileSheet = tileSheet;
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
    if (!Objects.equals(this.surface, other.surface)) {
      return false;
    }
    return Objects.equals(this.tileSheet, other.tileSheet);
  }

  public int getFirstGid() {
    return firstGid;
  }

  public int getLastGid() {
    return lastGid;
  }

  public String getName() {
    return name;
  }

  public Surface getSurface() {
    return surface;
  }

  public Tile getTile(int gid) {
    // Normalize the GID so that it is 0-based
    int normalizedGid = gid - firstGid;

    return tileSheet.getTile(
            normalizedGid % tileSheet.getWidth(),
            normalizedGid / tileSheet.getWidth());
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 29 * hash + Objects.hashCode(this.name);
    hash = 29 * hash + this.firstGid;
    hash = 29 * hash + this.lastGid;
    hash = 29 * hash + Objects.hashCode(this.surface);
    hash = 29 * hash + Objects.hashCode(this.tileSheet);
    return hash;
  }
}
