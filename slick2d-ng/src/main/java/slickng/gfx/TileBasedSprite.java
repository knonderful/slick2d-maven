package slickng.gfx;

import static java.util.Objects.requireNonNull;

public class TileBasedSprite implements Sprite {

  private final Tile tile;

  public TileBasedSprite(Tile tile) {
    this.tile = requireNonNull(tile, "Argument tile must be non-null.");
  }

  @Override
  public void render(Renderer2D renderer, float x, float y) {
    renderer.renderTile(tile, x, y);
  }
}
