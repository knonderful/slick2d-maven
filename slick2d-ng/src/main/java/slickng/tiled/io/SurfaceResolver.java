package slickng.tiled.io;

import java.io.IOException;
import slickng.UnsupportedFormatException;
import slickng.gfx.Surface;

@FunctionalInterface
public interface SurfaceResolver {

  Surface resolve(String source) throws IOException, UnsupportedFormatException;
}
