package slickng;

public interface Game {

  public void init(GameContext container) throws SlickException;

  public void update(GameContext container, int delta) throws SlickException;

  public void render(GameContext container, Graphics graphics) throws SlickException;

  public boolean close();
}
