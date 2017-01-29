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
  private final int width;
  private final int height;

  public CompositeRenderable(Map<Vec2f, Renderable> renderables) {
    this.renderables = new HashMap<>(renderables);

    // Calculate width and height
    float maxWidth = 0f;
    float maxHeight = 0f;
    for (Map.Entry<Vec2f, Renderable> entry : renderables.entrySet()) {
      Vec2f offset = entry.getKey();
      Renderable renderable = entry.getValue();

      float curWidth = offset.x + renderable.getWidth();
      if (curWidth > maxWidth) {
        maxWidth = curWidth;
      }

      float curHeight = offset.y + renderable.getHeight();
      if (curHeight > maxHeight) {
        maxHeight = curHeight;
      }
    }

    this.width = (int) maxWidth;
    this.height = (int) maxHeight;
  }

  @Override
  public void draw(float x, float y) {
    renderables.forEach((offset, renderable) -> {
      renderable.draw(x + offset.x, y + offset.y);
    });
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

}
