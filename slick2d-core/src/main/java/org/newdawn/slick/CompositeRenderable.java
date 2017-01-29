package org.newdawn.slick;

import com.sun.javafx.geom.Vec2f;
import java.util.HashMap;
import java.util.Map;

/**
 * A {@link Renderable} that is composed of multiple other {@link Renderable}
 * instances.
 */
public class CompositeRenderable implements Renderable {

  private final Map<Vec2f, Renderable> renderables;

  public CompositeRenderable(Map<Vec2f, Renderable> renderables) {
    this.renderables = new HashMap<>(renderables);


  }

  @Override
  public void draw(float x, float y) {
    renderables.forEach((offset, renderable) -> {
      renderable.draw(x + offset.x, y + offset.y);
    });
  }
}
