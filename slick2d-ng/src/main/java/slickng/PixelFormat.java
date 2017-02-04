package slickng;

public enum PixelFormat {
  RGBA(4);
  
  private final int bytesPerPixel;

  private PixelFormat(int bytesPerPixel) {
    this.bytesPerPixel = bytesPerPixel;
  }
  
  public int getBytesPerPixel() {
    return bytesPerPixel;
  }
}
