package slickng.lwjgl;

import java.util.logging.Level;
import java.util.logging.Logger;
import slickng.Game;
import slickng.GameContainer;
import slickng.InitContext;
import slickng.Input;
import slickng.SlickException;
import slickng.UpdateContext;
import slickng.gfx.Display;
import slickng.gfx.SurfaceTemplateFactory;
import slickng.lwjgl.gfx.OpenGlGraphics;
import slickng.lwjgl.gfx.OpenGlGraphicsOptions;
import slickng.lwjgl.input.LwjglInput;

import static java.util.Objects.requireNonNull;

/**
 * A {@link GameContainer} implementation that is implemented using the LWJGL
 * library.
 */
public class LwjlGameContainer implements GameContainer {

  private static final Logger LOG = Logger.getLogger(LwjlGameContainer.class.getName());

  private final Game game;
  private final OpenGlGraphics graphics;
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
  public LwjlGameContainer(Game game, int width, int height, boolean fullscreen, int frameRate) throws SlickException {
    this.game = requireNonNull(game, "Argument game must be non-null.");
    this.graphics = new OpenGlGraphics(OpenGlGraphicsOptions.getDefault()
            .setDisplayWidth(width)
            .setDisplayHeight(height)
            .setFullscreen(false)
            .setFpsLimit(frameRate)
    );
    this.deltaStep = 1000f / frameRate;
    this.gameContext = new GameContext(new LwjglInput(), graphics);
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
      Display display = graphics.init();
      game.init(gameContext);

      running = true;

      while (running) {
        if (!display.isActive()) {
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

        // Update the display
        display.update();

        // Check for display close requests
        if (display.isCloseRequested()) {
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
    private final OpenGlGraphics graphics;

    GameContext(Input input, OpenGlGraphics graphics) {
      this.input = requireNonNull(input, "Argument input must be non-null.");
      this.graphics = requireNonNull(graphics, "Argument graphics must be non-null.");
    }

    @Override
    public SurfaceTemplateFactory getSurfaceTemplateFactory() {
      return graphics.getSurfaceTemplateFactory();
    }

    @Override
    public Input getInput() {
      return input;
    }
  }
}
