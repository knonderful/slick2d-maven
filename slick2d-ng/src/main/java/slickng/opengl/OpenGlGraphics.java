package slickng.opengl;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import slickng.Graphics;
import slickng.RenderContext;
import slickng.SlickException;
import slickng.SurfaceFactory;

/**
 * An OpenGL-based {@link Graphics} implementation.
 */
public class OpenGlGraphics implements Graphics {

  private static final Logger LOG = Logger.getLogger(OpenGlGraphics.class.getName());

  private final SGL sgl = new ImmediateModeOGLRenderer();
  private final OpenGlSurfaceFactory surfaceFactory = new OpenGlSurfaceFactory(sgl);

  private final DisplayMode displayMode;
  private final int frameSync;

  private static String displayModeFailMessage(int width, int height, boolean fullscreen) {
    return String.format("Could not set display to %dx%d%s.", width, height, fullscreen ? " (fullscreen)" : "");
  }

  /**
   * Creates a new instance, instantiating the display mode and the OpenGL
   * context with the specified parameters.
   *
   * @param options The OpenGL graphics options.
   * @throws SlickException If the graphics could not be created with the
   *                        specified parameters.
   */
  public OpenGlGraphics(OpenGlGraphicsOptions options) throws SlickException {
    this.frameSync = options.getFrameSync();
    int width = options.getWidth();
    int height = options.getHeight();
    boolean fullscreen = options.isFullscreen();

    DisplayMode originalDisplayMode = Display.getDisplayMode();

    try {
      DisplayMode targetDisplayMode = null;
      if (fullscreen) {
        int freq = 0;

        for (DisplayMode current : Display.getAvailableDisplayModes()) {
          if ((current.getWidth() == width) && (current.getHeight() == height)) {
            if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
              if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                targetDisplayMode = current;
                freq = targetDisplayMode.getFrequency();
              }
            }

            // if we've found a match for bpp and frequency against the 
            // original display mode then it's probably best to go for this one
            // since it's most likely compatible with the monitor
            if ((current.getBitsPerPixel() == originalDisplayMode.getBitsPerPixel())
                    && (current.getFrequency() == originalDisplayMode.getFrequency())) {
              targetDisplayMode = current;
              break;
            }
          }
        }
      } else {
        targetDisplayMode = new DisplayMode(width, height);
      }

      if (targetDisplayMode == null) {
        throw new SlickException(displayModeFailMessage(width, height, fullscreen));
      }

      Display.setDisplayMode(targetDisplayMode);
      Display.setFullscreen(fullscreen);

      this.displayMode = targetDisplayMode;
    } catch (LWJGLException e) {
      throw new SlickException(displayModeFailMessage(width, height, fullscreen), e);
    }
  }

  @Override
  public void deinit() {
    Display.destroy();
  }

  @Override
  public void finishRender() {
    sgl.flush();
    Display.update();

    if (frameSync > 0) {
      Display.sync(frameSync);
    }
  }

  @Override
  public int getHeight() {
    return displayMode.getHeight();
  }

  @Override
  public SurfaceFactory getSurfaceFactory() {
    return surfaceFactory;
  }

  @Override
  public int getWidth() {
    return displayMode.getWidth();
  }

  @Override
  public boolean hasFocus() {
    return Display.isActive();
  }

  @Override
  public void init() throws SlickException {
    LOG.info(String.format("LWJGL Version: %s", Sys.getVersion()));
    LOG.info(String.format("Display Mode: %s", displayMode));

    // TODO: What do we need this privileged action for?
    AccessController.doPrivileged(new PrivilegedAction() {
      public Object run() {
        final boolean stencil = true;
        final int samples = 0;

        try {
          PixelFormat format = new PixelFormat(8, 8, stencil ? 8 : 0, samples);

          Display.create(format);
          return null;
        } catch (LWJGLException e) {
          LOG.log(Level.FINE, "Could not create display.", e);
          Display.destroy();
        }

        try {
          PixelFormat format = new PixelFormat(8, 8, stencil ? 8 : 0);

          Display.create(format);
          return null;
        } catch (LWJGLException e) {
          LOG.log(Level.FINE, "Could not create display.", e);
          Display.destroy();
          // if we couldn't get alpha, let us know
        }

        try {
          Display.create(new PixelFormat());
        } catch (LWJGLException e) {
          LOG.log(Level.SEVERE, "Could not create display.", e);
        }

        return null;
      }
    });

    if (!Display.isCreated()) {
      throw new SlickException("Failed to initialise the LWJGL display");
    }

    int width = getWidth();
    int height = getHeight();

    sgl.initDisplay(width, height);
    sgl.enterOrtho(width, height);
  }

  @Override
  public boolean isCloseRequested() {
    return Display.isCloseRequested();
  }

  @Override
  public RenderContext startRender() {
    // Blank the OpenGL surface
    sgl.glColor4f(0f, 0f, 0f, 0f);
    sgl.glClear(SGL.GL_COLOR_BUFFER_BIT | SGL.GL_DEPTH_BUFFER_BIT);
    // Load identity matrix
    sgl.glLoadIdentity();

    return null;
  }
}
