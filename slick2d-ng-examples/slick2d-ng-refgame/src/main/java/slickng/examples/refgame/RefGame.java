package slickng.examples.refgame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import slickng.Color;
import slickng.lwjgl.LwjlGameContainer;
import slickng.Game;
import slickng.InitContext;
import slickng.RenderContext;
import slickng.SlickException;
import slickng.UpdateContext;
import slickng.gfx.CompositeSprite;
import slickng.gfx.PngImageDataReader;
import slickng.gfx.Surface;
import slickng.gfx.SurfaceLibrary;
import slickng.gfx.TileSheet;
import slickng.tiled.TMap;
import slickng.tiled.io.TMapReader;

/**
 * A reference {@link Game} implementation.
 */
public class RefGame implements Game {

  private static final String MEGAMAN_PARTS = "megaman_parts";

  private final SurfaceLibrary surfaceLibrary = new SurfaceLibrary();
  private CompositeSprite sprite;

  @Override
  public void deinit() {
    // To be implemented
  }

  @Override
  public void init(InitContext context) throws SlickException {
    InputStream pngStream = getResourceStream("resources/megaman_parts.png");
    Surface surf = context.createSurface(new PngImageDataReader(new Color(255, 0, 255)), pngStream);
    surfaceLibrary.add(MEGAMAN_PARTS, surf);

    TileSheet tileSheet = surf.createTileSheet(8, 8);
    sprite = new CompositeSprite(9);

    // Head
    sprite.add(4, 0, tileSheet.getTile(7, 0));
    sprite.add(12, 0, tileSheet.getTile(8, 0));
    // Torso
    sprite.add(0, 8, tileSheet.getTile(9, 1));
    sprite.add(8, 8, tileSheet.getTile(10, 1));
    sprite.add(16, 8, tileSheet.getTile(11, 1));
    // Legs
    sprite.add(0, 16, tileSheet.getTile(9, 2));
    sprite.add(8, 16, tileSheet.getTile(10, 2));
    sprite.add(16, 16, tileSheet.getTile(11, 2));
    // Face
    sprite.add(7, 6, tileSheet.getTile(0, 0));

    TMapReader mapReader = new TMapReader(source -> {
      InputStream stream = getResourceStream("resources/" + source);
      // TODO: get transparency from TMX file...
      return context.createSurface(new PngImageDataReader(new Color(255, 0, 255)), stream);
    });

    TMap map;
    try {
      map = mapReader.read(getResourceStream("resources/testlevel_new.tmx"));
    } catch (IOException | ParserConfigurationException | SAXException | SlickException e) {
      throw new SlickException("Could not not read map file.", e);
    }
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
    context.scale(2.0f, 2.0f);

    context.with(surfaceLibrary.get(MEGAMAN_PARTS), renderer -> {
      sprite.render(renderer, 16f, 16f);
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
    LwjlGameContainer container = new LwjlGameContainer(new RefGame(), 640, 480, false, 60);
    container.start();
  }

}
