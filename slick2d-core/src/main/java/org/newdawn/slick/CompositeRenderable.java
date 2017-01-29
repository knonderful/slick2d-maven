package org.newdawn.slick;

import com.sun.javafx.geom.Vec2f;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A {@link Renderable} that is composed of multiple other {@link Renderable}
 * instances.
 * <p>
 * Note that the {@link Renderable}s get rendered in the order that they are
 * added.
 */
public class CompositeRenderable implements Renderable {

  private final Map<Vec2f, Renderable> renderables;

  public CompositeRenderable(int nrOfImages) {
    this.renderables = new LinkedHashMap<>(nrOfImages);
  }

  public void add(float offsetX, float offsetY, Renderable renderable) {
    renderables.put(new Vec2f(offsetX, offsetY), renderable);
  }

  @Override
  public void draw(float x, float y) {
    renderables.forEach((offset, renderable) -> {
      renderable.draw(x + offset.x, y + offset.y);
    });
  }
}
