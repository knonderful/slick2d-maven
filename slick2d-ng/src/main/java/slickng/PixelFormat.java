package slickng;

public enum PixelFormat {
  RGBA(32);
  
  private final int bytesPerPixel;

  private PixelFormat(int bytesPerPixel) {
    this.bytesPerPixel = bytesPerPixel;
  }
  
  public int getBytesPerPixel() {
    return bytesPerPixel;
  }
}
