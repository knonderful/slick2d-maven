package slickng.gfx;

import java.util.LinkedHashMap;
import java.util.Map;
import slickng.geom.Vector2f;

/**
 * A {@link Sprite} that is composed of multiple tiles.
 * <p>
 * A call to {@link #render(slickng.SurfaceRenderer, float, float)} will render
 * the tiles in the order that they were provided to
 * {@link #add(float, float, slickng.Tile)}.
 */
public class CompositeSprite implements Sprite {

  private final Map<Vector2f, Tile> mapping;

  public CompositeSprite(int nrOfTiles) {
    this.mapping = new LinkedHashMap<>(nrOfTiles);
  }

  /**
   * Adds a {@link Tile}.
   *
   * @param x    The X-coordinate of the tile in the {@link Sprite}.
   * @param y    The Y-coordinate of the tile in the {@link Sprite}.
   * @param tile The tile.
   */
  public void add(float x, float y, Tile tile) {
    mapping.put(new Vector2f(x, y), tile);
  }

  @Override
  public void render(Renderer2D renderer, float x, float y) {
    mapping.forEach((offset, tile) -> {
      renderer.renderTile(tile, x + offset.getX(), x + offset.getY());
    });
  }
}
