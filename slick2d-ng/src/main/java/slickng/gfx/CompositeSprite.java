package slickng.gfx;

import java.util.LinkedHashMap;
import java.util.Map;
import slickng.geom.Vector2f;

/**
 * A {@link Sprite} that is composed of multiple sub-{@link Sprite}s.
 * <p>
 * A call to {@link #render(slickng.SurfaceRenderer, float, float)} will render
 * the tiles in the order that they were provided to
 * {@link #add(float, float, slickng.Tile)}.
 */
public class CompositeSprite implements Sprite {

  private final Map<Vector2f, Sprite> mapping;

  public CompositeSprite(int nrOfTiles) {
    this.mapping = new LinkedHashMap<>(nrOfTiles);
  }

  /**
   * Adds a sub-{@link Sprite}.
   *
   * @param x      The X-coordinate of the sub-{@link Sprite} in the (composite)
   *               {@link Sprite}.
   * @param y      The Y-coordinate of the sub-{@link Sprite} in the (composite)
   *               {@link Sprite}.
   * @param sprite The {@link Sprite}.
   */
  public void add(float x, float y, Sprite sprite) {
    mapping.put(new Vector2f(x, y), sprite);
  }

  @Override
  public void render(Renderer2D renderer) {
    mapping.forEach((offset, sprite) -> {
      renderer.translate(offset.getX(), offset.getY());
      sprite.render(renderer);
      renderer.translate(-offset.getX(), -offset.getY());
    });
  }
}
