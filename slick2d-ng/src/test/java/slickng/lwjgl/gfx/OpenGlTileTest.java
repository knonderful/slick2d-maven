package slickng.lwjgl.gfx;

import slickng.lwjgl.gfx.OpenGlTile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link OpenGlTile}.
 */
public class OpenGlTileTest {

  private static final int DEFAULT_HEIGHT = 8;
  private static final int DEFAULT_TEXT_HEIGHT = 32;
  private static final int DEFAULT_TEXT_WIDTH = 64;

  private static final int DEFAULT_WIDTH = 8;

  private static final int DEFAULT_X = 8;
  private static final int DEFAULT_Y = 16;
  private static final float FLOAT_DELTA = 0.00001f;

  private OpenGlTile withDefaultTile() {
    return withTile(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TEXT_WIDTH, DEFAULT_TEXT_HEIGHT);
  }

  private OpenGlTile withTile(int x, int y, int width, int height, int textureWidth, int textureHeight) {
    return new OpenGlTile(x, y, width, height, textureWidth, textureHeight);
  }

  @Test
  public void testGetHeight() {
    OpenGlTile tile = withDefaultTile();
    assertEquals(DEFAULT_HEIGHT, tile.getHeight());
  }

  @Test
  public void testGetOffsetX() {
    OpenGlTile tile = withDefaultTile();
    assertEquals(DEFAULT_X, tile.getOffsetX());
  }

  @Test
  public void testGetOffsetY() {
    OpenGlTile tile = withDefaultTile();
    assertEquals(DEFAULT_Y, tile.getOffsetY());
  }

  @Test
  public void testGetWidth() {
    OpenGlTile tile = withDefaultTile();
    assertEquals(DEFAULT_WIDTH, tile.getWidth());
  }

  @Test
  public void testGetTx1() {
    OpenGlTile tile = withTile(16, 16, 8, 8, 64, 64);
    assertEquals(0.25f, tile.getTx1(), FLOAT_DELTA);

    tile = withTile(8, 16, 8, 8, 64, 64);
    assertEquals(0.125f, tile.getTx1(), FLOAT_DELTA);

    tile = withTile(0, 16, 8, 8, 64, 64);
    assertEquals(0f, tile.getTx1(), FLOAT_DELTA);

    tile = withTile(56, 16, 8, 8, 64, 64);
    assertEquals(0.875f, tile.getTx1(), FLOAT_DELTA);

    tile = withTile(16, 16, 8, 8, 64, 128);
    assertEquals(0.25f, tile.getTx1(), FLOAT_DELTA);

    tile = withTile(16, 16, 8, 8, 128, 64);
    assertEquals(0.125f, tile.getTx1(), FLOAT_DELTA);
  }

  @Test
  public void testGetTx2() {
    OpenGlTile tile = withTile(16, 16, 8, 8, 64, 64);
    assertEquals(0.375f, tile.getTx2(), FLOAT_DELTA);

    tile = withTile(8, 16, 8, 8, 64, 64);
    assertEquals(0.25f, tile.getTx2(), FLOAT_DELTA);

    tile = withTile(0, 16, 8, 8, 64, 64);
    assertEquals(0.125f, tile.getTx2(), FLOAT_DELTA);

    tile = withTile(56, 16, 8, 8, 64, 64);
    assertEquals(1f, tile.getTx2(), FLOAT_DELTA);

    tile = withTile(16, 16, 8, 8, 64, 128);
    assertEquals(0.375f, tile.getTx2(), FLOAT_DELTA);

    tile = withTile(16, 16, 8, 8, 128, 64);
    assertEquals(0.1875f, tile.getTx2(), FLOAT_DELTA);
  }

  @Test
  public void testGetTy1() {
    OpenGlTile tile = withTile(16, 16, 8, 8, 64, 64);
    assertEquals(0.25f, tile.getTy1(), FLOAT_DELTA);

    tile = withTile(16, 8, 8, 8, 64, 64);
    assertEquals(0.125f, tile.getTy1(), FLOAT_DELTA);

    tile = withTile(16, 0, 8, 8, 64, 64);
    assertEquals(0f, tile.getTy1(), FLOAT_DELTA);

    tile = withTile(16, 56, 8, 8, 64, 64);
    assertEquals(0.875f, tile.getTy1(), FLOAT_DELTA);

    tile = withTile(16, 16, 8, 8, 128, 64);
    assertEquals(0.25f, tile.getTy1(), FLOAT_DELTA);

    tile = withTile(16, 16, 8, 8, 64, 128);
    assertEquals(0.125f, tile.getTy1(), FLOAT_DELTA);
  }

  @Test
  public void testGetTy2() {
    OpenGlTile tile = withTile(16, 16, 8, 8, 64, 64);
    assertEquals(0.375f, tile.getTy2(), FLOAT_DELTA);

    tile = withTile(16, 8, 8, 8, 64, 64);
    assertEquals(0.25f, tile.getTy2(), FLOAT_DELTA);

    tile = withTile(16, 0, 8, 8, 64, 64);
    assertEquals(0.125f, tile.getTy2(), FLOAT_DELTA);

    tile = withTile(16, 56, 8, 8, 64, 64);
    assertEquals(1f, tile.getTy2(), FLOAT_DELTA);

    tile = withTile(16, 16, 8, 8, 128, 64);
    assertEquals(0.375f, tile.getTy2(), FLOAT_DELTA);

    tile = withTile(16, 16, 8, 8, 64, 128);
    assertEquals(0.1875f, tile.getTy2(), FLOAT_DELTA);
  }

}
