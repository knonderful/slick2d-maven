package slickng.examples.refgame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import slickng.Color;
import slickng.Game;
import slickng.InitContext;
import slickng.Keyboard;
import slickng.RenderContext;
import slickng.SlickException;
import slickng.UnsupportedFormatException;
import slickng.UpdateContext;
import slickng.gfx.CompositeSprite;
import slickng.gfx.ImageBuffer;
import slickng.gfx.Indexed8Writer;
import slickng.gfx.PaletteBasedSprite;
import slickng.gfx.PixelFormat;
import slickng.gfx.PngImageDataReader;
import slickng.gfx.Renderer2D;
import slickng.gfx.Rgba8Indexed8Converter;
import slickng.gfx.Rgba8Pixel;
import slickng.gfx.Rgba8Reader;
import slickng.gfx.Rgba8Writer;
import slickng.gfx.Surface;
import slickng.gfx.SurfaceLibrary;
import slickng.gfx.SurfaceTemplate;
import slickng.gfx.SurfaceTemplateConverter;
import slickng.gfx.SurfaceTemplateFactory;
import slickng.gfx.TileBasedSprite;
import slickng.gfx.TileSheet;
import slickng.lwjgl.LwjlGameContainer;
import slickng.tiled.TMap;
import slickng.tiled.io.TMapReader;

/**
 * A reference {@link Game} implementation.
 */
public class RefGame implements Game {

  private static final String MEGAMAN_PARTS = "megaman_parts";

  private final SurfaceLibrary surfaceLibrary = new SurfaceLibrary();
  private CompositeSprite sprite;
  private Surface palette;
  private Surface indexedSurface;
  private float posX = 32f;
  private float posY = 32f;

  @Override
  public void deinit() {
    // To be implemented
  }

  @Override
  public void init(InitContext context) throws SlickException {
    SurfaceTemplateFactory stf = context.getSurfaceTemplateFactory();

    Rgba8Pixel[] paletteColors = {
      // Group 1
      // 0: transparent
      new Rgba8Pixel(255, 0, 255, 0),
      // 1: general color #1 (black)
      new Rgba8Pixel(0, 0, 0, 255),
      // 2: general color #2 (skin color)
      new Rgba8Pixel(252, 216, 168, 255),
      // 3: general color #3 (white)
      new Rgba8Pixel(255, 255, 255, 255),
      // Group 2
      // 4: transparent
      new Rgba8Pixel(255, 0, 255, 0),
      // 5: mega man color 1 (black)
      new Rgba8Pixel(0, 0, 0, 255),
      // 6: mega man color 2 (dark blue)
      new Rgba8Pixel(0, 112, 236, 255),
      // 7: mega man color 3 (light blue)
      new Rgba8Pixel(0, 232, 216, 255)
    };

    this.palette = createPalette(stf, paletteColors);
    this.indexedSurface = createIndexedSurface(stf);

    Map<Rgba8Pixel, Byte> conversionMap = new HashMap<>(4);
    conversionMap.put(new Rgba8Pixel(116, 116, 116), (byte) 0);
    conversionMap.put(new Rgba8Pixel(0, 0, 0), (byte) 1);
    conversionMap.put(new Rgba8Pixel(0, 112, 236), (byte) 2);
    conversionMap.put(new Rgba8Pixel(0, 232, 216), (byte) 3);

    Surface surf;
    PngImageDataReader reader = new PngImageDataReader(new Color(255, 0, 255));
    try (InputStream pngStream = getResourceStream("resources/megaman_parts.png")) {
      SurfaceTemplate srcTemplate = reader.read(stf, pngStream);
      SurfaceTemplate destTemplate = stf.create(PixelFormat.INDEXED_8, srcTemplate.getBuffer().getImageWidth(), srcTemplate.getBuffer().getImageHeight());
      SurfaceTemplateConverter<Rgba8Pixel, Byte> converter = new SurfaceTemplateConverter<>(
              new Rgba8Indexed8Converter(conversionMap), Rgba8Reader::new, Indexed8Writer::new);

      converter.convert(srcTemplate, destTemplate);

      surf = destTemplate.createSurface();
    } catch (IOException e) {
      throw new SlickException(String.format("I/O error while trying to load the graphics data."), e);
    }

    surfaceLibrary.add(MEGAMAN_PARTS, surf);

    TileSheet tileSheet = surf.createTileSheet(8, 8);
    sprite = new CompositeSprite(9);

    // First index in the palette for the body colors
    int bodyIndex = 4;
    // First index in the palette for the face colors
    int faceIndex = 0;

    // Head
    sprite.add(4, 0, new PaletteBasedSprite(new TileBasedSprite(tileSheet.getTile(7, 0)), bodyIndex));
    sprite.add(12, 0, new PaletteBasedSprite(new TileBasedSprite(tileSheet.getTile(8, 0)), bodyIndex));
    // Torso
    sprite.add(0, 8, new PaletteBasedSprite(new TileBasedSprite(tileSheet.getTile(9, 1)), bodyIndex));
    sprite.add(8, 8, new PaletteBasedSprite(new TileBasedSprite(tileSheet.getTile(10, 1)), bodyIndex));
    sprite.add(16, 8, new PaletteBasedSprite(new TileBasedSprite(tileSheet.getTile(11, 1)), bodyIndex));
    // Legs
    sprite.add(0, 16, new PaletteBasedSprite(new TileBasedSprite(tileSheet.getTile(9, 2)), bodyIndex));
    sprite.add(8, 16, new PaletteBasedSprite(new TileBasedSprite(tileSheet.getTile(10, 2)), bodyIndex));
    sprite.add(16, 16, new PaletteBasedSprite(new TileBasedSprite(tileSheet.getTile(11, 2)), bodyIndex));
    // Face
    sprite.add(7, 6, new PaletteBasedSprite(new TileBasedSprite(tileSheet.getTile(0, 0)), faceIndex));

    TMap map;
    try {
      map = new TMapReader().read(getResourceStream("resources/testlevel_new.tmx"));
    } catch (IOException | ParserConfigurationException | SAXException | SlickException e) {
      throw new SlickException("Could not not read map file.", e);
    }
  }

  private Surface createIndexedSurface(SurfaceTemplateFactory stf) throws UnsupportedFormatException {
    // An 10x6 indexed image
    byte[] image = {
      0, 0, 2, 0, 4, 4, 0, 2, 0, 0,
      0, 2, 0, 4, 3, 3, 4, 0, 2, 0,
      2, 0, 4, 5, 3, 3, 5, 4, 0, 2,
      0, 4, 5, 6, 5, 5, 6, 5, 4, 0,
      0, 0, 4, 6, 4, 4, 6, 4, 0, 0,
      0, 0, 4, 4, 0, 0, 4, 4, 0, 0
    };

    SurfaceTemplate template = stf.create(PixelFormat.RGBA_8, 10, 6);
    ImageBuffer buf = template.getBuffer();
    for (int i : image) {
      buf.writeByte(i); // r
      buf.writeByte(0); // g
      buf.writeByte(0); // b
      buf.writeByte(255); // a
    }
    return template.createSurface();
  }

  private Surface createPalette(SurfaceTemplateFactory stf, Rgba8Pixel... colors) throws UnsupportedFormatException {
    SurfaceTemplate template = stf.create(PixelFormat.RGBA_8, 256, 1);
    Rgba8Writer writer = new Rgba8Writer(template.getBuffer());
    for (Rgba8Pixel color : colors) {
      writer.write(color);
    }
    return template.createSurface();
  }

  private InputStream getResourceStream(String path) throws IOException {
    return new FileInputStream(new File(path));
  }

  @Override
  public void render(RenderContext context) throws SlickException {
    Renderer2D r = context.getRenderer2D()
            .scale(2f, 2f)
            .translate(posX, posY)
            .setImage(surfaceLibrary.get(MEGAMAN_PARTS))
            .setPalette(palette);

    sprite.render(r);
  }

  @Override
  public boolean requestClose() {
    return true;
  }

  @Override
  public void update(UpdateContext context, int delta) throws SlickException {
    Keyboard kb = context.getInput().getKeyboard();
    if (kb.isPressed(Keyboard.KEY_LEFT)) {
      this.posX--;
    }
    if (kb.isPressed(Keyboard.KEY_RIGHT)) {
      this.posX++;
    }
    if (kb.isPressed(Keyboard.KEY_UP)) {
      this.posY--;
    }
    if (kb.isPressed(Keyboard.KEY_DOWN)) {
      this.posY++;
    }
  }

  public static void main(String[] args) throws SlickException {
    LwjlGameContainer container = new LwjlGameContainer(new RefGame(), 640, 480, false, 60);
    container.start();
  }

}
