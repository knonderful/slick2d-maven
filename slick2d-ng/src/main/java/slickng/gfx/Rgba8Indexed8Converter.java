package slickng.gfx;

import java.util.HashMap;
import java.util.Map;
import slickng.SlickException;

/**
 * Converts a {@link PixelFormat#RGBA_8} pixel into a
 * {@link PixelFormat#INDEXED_8} pixel using a conversion map.
 */
public class Rgba8Indexed8Converter implements PixelConverter<Rgba8Pixel, Byte> {

  private final Map<Rgba8Pixel, Byte> conversionMap;

  public Rgba8Indexed8Converter(Map<Rgba8Pixel, Byte> conversionMap) {
    this.conversionMap = new HashMap<>(conversionMap);
  }

  @Override
  public Byte convert(Rgba8Pixel source) throws SlickException {
    Byte index = conversionMap.get(source);
    if (index == null) {
      throw new SlickException(String.format("No target index for color: %s.", source));
    }
    return index;
  }
}
