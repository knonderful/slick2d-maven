package slickng.gfx;

import slickng.SlickException;

public interface PixelConverter<S, D> {

  D convert(S source) throws SlickException;

}
