package slickng;

public interface TileSheet {

  Tile getTile(int indexX, int indexY);

  int getTileWidth();

  int getTileHeight();
}
