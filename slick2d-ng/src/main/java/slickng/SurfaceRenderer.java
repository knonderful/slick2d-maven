package slickng;

/**
 * A renderer for {@link Surface}s.
 */
public interface SurfaceRenderer {

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
   * Renders a fragment of the surface with the provided location. The render
   * target will have the size of the fragment.
   *
   * @param x          The X-position at which to render.
   * @param y          The Y-position at which to render.
   * @param fragOffX   The X-offset of the fragment in the surface.
   * @param fragOffY   The Y-offset of the fragment in the surface.
   * @param fragWidth  The width of the fragment.
   * @param fragHeight The heigh of the fragment.
   */
  void renderFragment(float x, float y,
          float fragOffX, float fragOffY, float fragWidth, float fragHeight);

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
  void renderFragment(float x, float y, float width, float height,
          float fragOffX, float fragOffY, float fragWidth, float fragHeight);
}
