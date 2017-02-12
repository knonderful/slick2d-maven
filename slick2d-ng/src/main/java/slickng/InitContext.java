package slickng;

import slickng.gfx.SurfaceTemplateFactory;

/**
 * The context for {@link Game#init(slickng.InitContext)}.
 */
public interface InitContext {

  /**
   * Retrieves the {@link SurfaceTemplateFactory}.
   *
   * @return The {@link SurfaceTemplateFactory}.
   */
  SurfaceTemplateFactory getSurfaceTemplateFactory();

  /**
   * Gets the {@link Input}.
   *
   * @return The {@link Input}.
   */
  Input getInput();
}
