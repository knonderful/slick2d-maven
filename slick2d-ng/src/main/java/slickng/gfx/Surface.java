package slickng.gfx;

public interface Surface {

  int getWidth();
  
  int getHeight();

  TileSheet createTileSheet(int tileWidth, int tileHeight);
}
