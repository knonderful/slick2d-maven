package slickng;

import java.util.function.Consumer;

/**
 * The context for {@link Game#render(slickng.RenderContext)}.
 */
public interface RenderContext {

  // This allows for the renderer/graphics implementation to bind a surface
  // while the consumer can perform multiple operations on that surface. No
  // multiple bind() calls are needed, thus saving processing power. =)
  void with(Surface surface, Consumer<SurfaceOperations> consumer);

  public interface SurfaceOperations {

    /**
     * Renders the entire surface with the provided location.
     *
     * @param x The X-position at which to render.
     * @param y The Y-position at which to render.
     */
    void render(float x, float y);

    /**
     * Renders the entire surface with the provided location and dimensions.
     *
     * @param x      The X-position at which to render.
     * @param y      The Y-position at which to render.
     * @param width  The width of the render target.
     * @param height The height of the render target.
     */
    void render(float x, float y, float width, float height);

    /**
     * Renders a fragment of the surface with the provided location and
     * dimensions.
     *
     * @param x          The X-position at which to render.
     * @param y          The Y-position at which to render.
     * @param width      The width of the render target.
     * @param height     The height of the render target.
     * @param fragOffX   The X-offset of the fragment in the surface.
     * @param fragOffY   The Y-offset of the fragment in the surface.
     * @param fragWidth  The width of the fragment.
     * @param fragHeight The heigh of the fragment.
     */
    void render(float x, float y, float width, float height,
            float fragOffX, float fragOffY, float fragWidth, float fragHeight);
  }
}
