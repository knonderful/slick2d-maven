package slickng.tiled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TMap {

  private final Collection<TTileSet> tileSets;
  private final List<TLayer> layers;

  public TMap(Collection<TTileSet> tileSets, List<TLayer> layers) {
    this.tileSets = new ArrayList<>(tileSets);
    this.layers = new ArrayList<>(layers);
  }

  public List<TLayer> getLayers() {
    return Collections.unmodifiableList(layers);
  }

  public Collection<TTileSet> getTileSets() {
    return Collections.unmodifiableCollection(tileSets);
  }

  public TTileSet getTileSetForGid(int gid) {
    return tileSets.stream()
            .filter(ts -> ts.getFirstGid() >= gid && ts.getLastGid() <= gid)
            .findAny()
            .orElse(null);
  }
}
