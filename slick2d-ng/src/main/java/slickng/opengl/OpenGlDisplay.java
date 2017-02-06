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
import slickng.SlickException;

/**
 * {@link Display} implementation for {@link OpenGlGraphics}.
 */
public class OpenGlDisplay implements slickng.gfx.Display {

  private static final Logger LOG = Logger.getLogger(OpenGlDisplay.class.getName());

  private final int sync;

  private static String displayModeFailMessage(int width, int height, boolean fullscreen) {
    return String.format("Could not set display to %dx%d%s.", width, height, fullscreen ? " (fullscreen)" : "");
  }

  OpenGlDisplay(int width, int height, boolean fullscreen, int sync) throws SlickException {
    LOG.info(String.format("LWJGL Version: %s", Sys.getVersion()));

    this.sync = sync;

    initDisplayMode(width, height, fullscreen);
    createDisplay();
  }

  private void initDisplayMode(int width, int height, boolean fullscreen) throws SlickException {
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

      LOG.info(String.format("Display Mode: %s", targetDisplayMode));

      Display.setDisplayMode(targetDisplayMode);
      Display.setFullscreen(fullscreen);

    } catch (LWJGLException e) {
      throw new SlickException(displayModeFailMessage(width, height, fullscreen), e);
    }
  }

  private void createDisplay() throws SlickException {
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
  }

  @Override
  public int getHeight() {
    return Display.getHeight();
  }

  @Override
  public int getWidth() {
    return Display.getWidth();
  }

  @Override
  public boolean isActive() {
    return Display.isActive();
  }

  @Override
  public boolean isCloseRequested() {
    return Display.isCloseRequested();
  }

  @Override
  public boolean isVisible() {
    return Display.isVisible();
  }

  @Override
  public void update() {
    Display.update();

    if (sync > 0) {
      Display.sync(sync);
    }
  }
}
