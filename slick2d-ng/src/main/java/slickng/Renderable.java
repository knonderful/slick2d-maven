package slickng;

/**
 * An entity that can be rendered to a {@link RenderContext}.
 */
public interface Renderable {

  /**
   * Render the entity.
   *
   * @param renderContext The {@link RenderContext}.
   */
  void render(RenderContext renderContext);
}
