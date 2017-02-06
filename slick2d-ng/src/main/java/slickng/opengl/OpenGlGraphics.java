package slickng.opengl;

import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
import slickng.RenderContext;
import slickng.SlickException;
import slickng.gfx.Graphics;
import slickng.gfx.ImageDataFactory;
import slickng.gfx.SurfaceFactory;

/**
 * An OpenGL-based {@link Graphics} implementation.
 */
public class OpenGlGraphics implements Graphics {

  private static final Logger LOG = Logger.getLogger(OpenGlGraphics.class.getName());

  private final SGL sgl = new ImmediateModeOGLRenderer();
  private final OpenGlRenderContext renderContext = new OpenGlRenderContext(sgl);
  private final OpenGlSurfaceFactory surfaceFactory = new OpenGlSurfaceFactory(sgl);
  private final OpenGlImageDataFactory imageDataFactory = new OpenGlImageDataFactory();

  private final OpenGlGraphicsOptions options;

  /**
   * Creates a new instance, instantiating the display mode and the OpenGL
   * context with the specified parameters.
   *
   * @param options The OpenGL graphics options.
   * @throws SlickException If the graphics could not be created with the
   *                        specified parameters.
   */
  public OpenGlGraphics(OpenGlGraphicsOptions options) throws SlickException {
    if (options == null) {
      this.options = OpenGlGraphicsOptions.getDefault();
    } else {
      this.options = options;
    }
  }

  @Override
  public void deinit() {
    Display.destroy();
  }

  @Override
  public void finishRender() {
    sgl.flush();
  }

  @Override
  public ImageDataFactory getImageDataFactory() {
    return imageDataFactory;
  }

  @Override
  public SurfaceFactory getSurfaceFactory() {
    return surfaceFactory;
  }

  @Override
  public slickng.gfx.Display init() throws SlickException {
    // Create the display
    OpenGlDisplay display = new OpenGlDisplay(options.getDisplayWidth(), options.getDisplayHeight(), options.isFullscreen(), options.getFpsLimit());

    // Set up the OpenGL context in sync with the display
    int width = display.getWidth();
    int height = display.getHeight();

    sgl.initDisplay(width, height);
    sgl.enterOrtho(width, height);

    return display;
  }

  @Override
  public RenderContext startRender() {
    // Blank the OpenGL surface
    sgl.glClearColor(0f, 0f, 0f, 1f);
    sgl.glClear(SGL.GL_COLOR_BUFFER_BIT | SGL.GL_DEPTH_BUFFER_BIT);

    // Load identity matrix
    sgl.glLoadIdentity();

    return renderContext;
  }
}
