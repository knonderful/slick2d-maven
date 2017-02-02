package slickng.opengl;

import slickng.Graphics;
import slickng.RenderContext;
import slickng.SurfaceFactory;

/**
 * An OpenGL-based {@link Graphics} implementation.
 */
public class OpenGlGraphics implements Graphics {

  private final SGL sgl = new ImmediateModeOGLRenderer();
  private final OpenGlSurfaceFactory surfaceFactory = new OpenGlSurfaceFactory(sgl);

  public void init(int width, int heigth, boolean fullscreen) {
    
  }
  
  @Override
  public RenderContext getRenderContext() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public SurfaceFactory getSurfaceFactory() {
    return surfaceFactory;
  }
}
