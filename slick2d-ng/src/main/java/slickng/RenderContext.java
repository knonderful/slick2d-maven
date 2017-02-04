package slickng;

import java.util.function.Consumer;

/**
 * The context for {@link Game#render(slickng.RenderContext)}.
 */
public interface RenderContext {

  // This allows for the renderer/graphics implementation to bind a surface
  // while the consumer can perform multiple operations on that surface. No
  // multiple bind() calls are needed, thus saving processing power. =)
  void with(Surface surface, Consumer<SurfaceRenderer> consumer);

}
