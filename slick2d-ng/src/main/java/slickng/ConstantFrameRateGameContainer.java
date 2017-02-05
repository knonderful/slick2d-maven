package slickng;

import slickng.gfx.ImageData;
import slickng.gfx.Graphics;
import slickng.gfx.Surface;
import slickng.gfx.ImageDataReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import slickng.opengl.OpenGlGraphics;
import slickng.opengl.OpenGlGraphicsOptions;

import static java.util.Objects.requireNonNull;

/**
 * A {@link GameContainer} implementation that runs a game at a constant frame
 * rate.
 * <p>
 * This implementation is not thread-safe.
 */
public class ConstantFrameRateGameContainer implements GameContainer {

  private static final Logger LOG = Logger.getLogger(ConstantFrameRateGameContainer.class.getName());

  private final Game game;
  private final Graphics graphics;
  private final float deltaStep;
  private final GameContext gameContext;

  private boolean running = false;
  private float deltaRest = 0f;

  /**
   * Creates a new instance.
   *
   * @param game       The {@link Game} implementation.
   * @param width      The width of the screen.
   * @param height     The height of the screen.
   * @param fullscreen A flag that specifies whether the game should be run in
   *                   full-screen mode.
   * @param frameRate  The frame rate at which the game should run, in frames
   *                   per second.
   * @throws SlickException If the container could not be created.
   */
  public ConstantFrameRateGameContainer(Game game, int width, int height, boolean fullscreen, int frameRate) throws SlickException {
    this.game = requireNonNull(game, "Argument game must be non-null.");
    this.graphics = new OpenGlGraphics(OpenGlGraphicsOptions.getDefault()
            .setDisplayWidth(width)
            .setDisplayHeight(height)
            .setFullscreen(false)
            .setFpsLimit(frameRate)
    );
    this.deltaStep = 1000f / frameRate;
    this.gameContext = new GameContext(new DummyInput(), graphics);
  }

  private int nextDelta() {
    float time = deltaRest + deltaStep;
    int rounded = (int) time;
    deltaRest = time - rounded;
    return rounded;
  }

  @Override
  public void start() throws SlickException {
    try {
      graphics.init();
      game.init(gameContext);

      running = true;

      while (running) {
        if (!graphics.hasFocus()) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
          }
        } else {
          // Game logic update
          try {
            int delta = nextDelta();
            game.update(gameContext, delta);
          } catch (SlickException e) {
            LOG.log(Level.SEVERE, "Game update method has thrown an exception.", e);
            running = false;
            break;
          }

          // Game render
          try {
            game.render(graphics.startRender());
            graphics.finishRender();
          } catch (SlickException e) {
            LOG.log(Level.SEVERE, "Game render method has thrown an exception.", e);
            running = false;
            break;
          }
        }

        // Check for display close requests
        if (graphics.isCloseRequested()) {
          if (game.requestClose()) {
            running = false;
          }
        }

      }
    } finally {
      graphics.deinit();
      game.deinit();
    }
  }

  @Override
  public void stop() {
    running = false;
  }

  private static class GameContext implements InitContext, UpdateContext {

    private final Input input;
    private final Graphics graphics;

    GameContext(Input input, Graphics graphics) {
      this.input = requireNonNull(input, "Argument input must be non-null.");
      this.graphics = requireNonNull(graphics, "Argument graphics must be non-null.");
    }

    @Override
    public Surface createSurface(ImageDataReader reader, InputStream inputStream) throws SlickException {
      try {
        ImageData imageData = reader.read(graphics.getImageDataFactory(), inputStream);
        return graphics.getSurfaceFactory().create(imageData);
      } catch (IOException e) {
        throw new SlickException("Could not create surface.", e);
      }
    }

    @Override
    public Input getInput() {
      return input;
    }
  }

  private static class DummyInput implements Input {
  }
}
