package slickng.tiled.io;

import slickng.SlickException;
import slickng.gfx.Surface;

@FunctionalInterface
public interface SurfaceResolver {

  Surface resolve(String source) throws SlickException;
}
