package slickng.lwjgl.gfx;

import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
import slickng.RenderContext;
import slickng.SlickException;
import slickng.gfx.Graphics;
import slickng.gfx.ImageDataFactory;
import slickng.gfx.SurfaceFactory;

import static org.lwjgl.opengl.GL11.*;

/**
 * An OpenGL-based {@link Graphics} implementation.
 */
public class OpenGlGraphics implements Graphics {

  private static final Logger LOG = Logger.getLogger(OpenGlGraphics.class.getName());

  private final OpenGlRenderContext renderContext = new OpenGlRenderContext();
  private final OpenGlSurfaceFactory surfaceFactory = new OpenGlSurfaceFactory();
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

  private void initOpenGl(int width, int height) {
    glEnable(GL_TEXTURE_2D);
    glShadeModel(GL_SMOOTH);
    glDisable(GL_DEPTH_TEST);
    glDisable(GL_LIGHTING);

    glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    glClearDepth(1);

    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    glViewport(0, 0, width, height);
    glMatrixMode(GL_MODELVIEW);
  }

  private void enterOrtho(int width, int height) {
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(0, width, height, 0, 1, -1);
    glMatrixMode(GL_MODELVIEW);
  }

  @Override
  public void deinit() {
    Display.destroy();
  }

  @Override
  public void finishRender() {
    glFlush();
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

    initOpenGl(width, height);
    enterOrtho(width, height);

    LOG.info(String.format("OpenGL version: %s", glGetString(GL_VERSION)));

    return display;
  }

  @Override
  public RenderContext startRender() {
    // Blank the OpenGL surface
    glClearColor(0f, 0f, 0f, 1f);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // Load identity matrix
    glLoadIdentity();

    return renderContext;
  }
}
