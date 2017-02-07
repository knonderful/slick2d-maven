package slickng.opengl;

import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
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
    GL11.glEnable(GL11.GL_TEXTURE_2D);
    GL11.glShadeModel(GL11.GL_SMOOTH);
    GL11.glDisable(GL11.GL_DEPTH_TEST);
    GL11.glDisable(GL11.GL_LIGHTING);

    GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    GL11.glClearDepth(1);

    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

    GL11.glViewport(0, 0, width, height);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
  }

  private void enterOrtho(int width, int height) {
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    GL11.glOrtho(0, width, height, 0, 1, -1);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
  }

  @Override
  public void deinit() {
    Display.destroy();
  }

  @Override
  public void finishRender() {
    GL11.glFlush();
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

    LOG.info(String.format("OpenGL version: %s", GL11.glGetString(GL11.GL_VERSION)));

    return display;
  }

  @Override
  public RenderContext startRender() {
    // Blank the OpenGL surface
    GL11.glClearColor(0f, 0f, 0f, 1f);
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

    // Load identity matrix
    GL11.glLoadIdentity();

    return renderContext;
  }
}
