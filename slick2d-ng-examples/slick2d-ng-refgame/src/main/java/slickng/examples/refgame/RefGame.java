package slickng.examples.refgame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import slickng.ConstantFrameRateGameContainer;
import slickng.Game;
import slickng.InitContext;
import slickng.PngImageDataReader;
import slickng.RenderContext;
import slickng.SlickException;
import slickng.Surface;
import slickng.UpdateContext;
import slickng.gfx.SurfaceLibrary;

/**
 * A reference {@link Game} implementation.
 */
public class RefGame implements Game {

  private static final String MEGAMAN_PARTS = "megaman_parts";

  private final SurfaceLibrary surfaceLibrary = new SurfaceLibrary();

  @Override
  public void deinit() {
    // To be implemented
  }

  @Override
  public void init(InitContext context) throws SlickException {
    InputStream pngStream = getResourceStream("resources/megaman_parts.png");
    Surface surf = context.createSurface(new PngImageDataReader(), pngStream);
    surfaceLibrary.add(MEGAMAN_PARTS, surf);
  }

  private InputStream getResourceStream(String path) throws SlickException {
    try {
      return new FileInputStream(new File(path));
    } catch (FileNotFoundException e) {
      throw new SlickException(String.format("Resource not found: %s", path), e);
    }
  }

  @Override
  public void render(RenderContext context) throws SlickException {
    context.with(surfaceLibrary.get(MEGAMAN_PARTS), ops -> {
      ops.render(10f, 10f);
    });
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
