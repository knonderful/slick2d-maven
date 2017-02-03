package slickng.examples.refgame;

import slickng.ConstantFrameRateGameContainer;
import slickng.Game;
import slickng.InitContext;
import slickng.RenderContext;
import slickng.SlickException;
import slickng.UpdateContext;

/**
 * A reference {@link Game} implementation.
 */
public class RefGame implements Game {

  @Override
  public void init(InitContext context) throws SlickException {
    // To be implemented
  }

  @Override
  public void render(RenderContext context) throws SlickException {
    // To be implemented
  }

  @Override
  public boolean requestClose() {
    return true;
  }

  @Override
  public void update(UpdateContext context, int delta) throws SlickException {
    // To be implemented
  }
  
  public static void main(String[] args) throws SlickException {
    ConstantFrameRateGameContainer container = new ConstantFrameRateGameContainer(new RefGame(), 640, 480, false, 60);
    container.start();
  }

}
