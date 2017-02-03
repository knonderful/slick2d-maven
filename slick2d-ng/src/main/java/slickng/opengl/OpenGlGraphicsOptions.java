package slickng.opengl;

public class OpenGlGraphicsOptions {

  private int width = 640;
  private int height = 480;
  private boolean fullscreen = false;
  private int frameSync = 0;

  private OpenGlGraphicsOptions() {
  }

  public static OpenGlGraphicsOptions getDefault() {
    return new OpenGlGraphicsOptions();
  }

  public int getFrameSync() {
    return frameSync;
  }

  public OpenGlGraphicsOptions setFrameSync(int frameSync) {
    this.frameSync = frameSync;
    return this;
  }

  public int getHeight() {
    return height;
  }

  public OpenGlGraphicsOptions setHeight(int height) {
    this.height = height;
    return this;
  }

  public int getWidth() {
    return width;
  }

  public OpenGlGraphicsOptions setWidth(int width) {
    this.width = width;
    return this;
  }

  public boolean isFullscreen() {
    return fullscreen;
  }

  public OpenGlGraphicsOptions setFullscreen(boolean fullscreen) {
    this.fullscreen = fullscreen;
    return this;
  }
}
