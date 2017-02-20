package slickng.tiled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A Tiled map (http://www.mapeditor.org/).
 * <p>
 * A Tile map consist of tile sets, each containing a multitude of tiles. The
 * tile sets specify the tiles that are available for creating layers. A layer
 * consists of a grid of tiles or object references.
 * <p>
 * Each tile in the map has a globally unique ID (often referred to as
 * {@code gid} by which it can be referenced from a layer.
 */
public class TMap {

  private final Collection<TTileSet> tileSets;
  private final List<TLayer> layers;

  /**
   * Creates a new instance.
   *
   * @param tileSets The tile sets.
   * @param layers   The layers.
   */
  public TMap(Collection<TTileSet> tileSets, List<TLayer> layers) {
    this.tileSets = new ArrayList<>(tileSets);
    this.layers = new ArrayList<>(layers);
  }

  /**
   * Retrieves the layers, sorted in the order in which they are represented in
   * the Tiled map editor.
   *
   * @return The layers.
   */
  public List<TLayer> getLayers() {
    return Collections.unmodifiableList(layers);
  }

  /**
   * Retrieves the tile sets.
   *
   * @return The tile sets.
   */
  public Collection<TTileSet> getTileSets() {
    return Collections.unmodifiableCollection(tileSets);
  }

  /**
   * Retrieves the tile set that contains the tile with the specified global ID.
   *
   * @param gid The global ID.
   * @return The tile set or {@code null} if no tile with the specified ID
   *         exists.
   */
  public TTileSet getTileSetForGid(int gid) {
    return tileSets.stream()
            .filter(ts -> ts.containsTile(gid))
            .findAny()
            .orElse(null);
  }
}
