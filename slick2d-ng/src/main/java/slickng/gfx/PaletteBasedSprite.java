package slickng.gfx;

import static java.util.Objects.requireNonNull;

public class PaletteBasedSprite implements Sprite {

  private final int paletteIndexOffset;
  private final Sprite sprite;

  public PaletteBasedSprite(Sprite sprite, int paletteIndexOffset) {
    this.sprite = requireNonNull(sprite, "Argument sprite must be non-null.");
    this.paletteIndexOffset = paletteIndexOffset;
  }

  @Override
  public void render(Renderer2D renderer, float x, float y) {
    renderer.setPaletteIndex(paletteIndexOffset);
    sprite.render(renderer, x, y);
  }

}
