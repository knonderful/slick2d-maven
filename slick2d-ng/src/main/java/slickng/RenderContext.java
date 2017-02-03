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
     * Renders the entire surface to the destination position.
     * @param destX
     * @param destY 
     */
    void render(float destX, float destY);
    
    /**
     * Renders the entire surface to the destination rectangle.
     * @param destX
     * @param destY
     * @param destWidth
     * @param destHeight 
     */
    void render(float destX, float destY, float destWidth, float destHeight);
    
    /**
     * Renders a rectangle from the surface to the destination rectangle.
     * 
     * @param destX
     * @param destY
     * @param destWidth
     * @param destHeight
     * @param srcX
     * @param srcY
     * @param srcWidth
     * @param srcHeight 
     */
    void render(float destX, float destY, float destWidth, float destHeight,
            float srcX, float srcY, float srcWidth, float srcHeight);
  }
}
