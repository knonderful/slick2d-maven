package slickng.gfx;

public class Rgba8Pixel {

  private static final byte MAX_BYTE = toByte(0xFF);

  private final byte red;
  private final byte green;
  private final byte blue;
  private final byte alpha;

  public Rgba8Pixel(byte r, byte g, byte b, byte a) {
    this.red = r;
    this.green = g;
    this.blue = b;
    this.alpha = a;
  }

  public Rgba8Pixel(byte r, byte g, byte b) {
    this.red = r;
    this.green = g;
    this.blue = b;
    this.alpha = MAX_BYTE;
  }

  public Rgba8Pixel(int r, int g, int b, int a) {
    this(toByte(r), toByte(g), toByte(b), toByte(a));
  }

  public Rgba8Pixel(int r, int g, int b) {
    this(toByte(r), toByte(g), toByte(b), MAX_BYTE);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Rgba8Pixel other = (Rgba8Pixel) obj;
    if (this.red != other.red) {
      return false;
    }
    if (this.green != other.green) {
      return false;
    }
    if (this.blue != other.blue) {
      return false;
    }
    return this.alpha == other.alpha;
  }

  public byte getAlpha() {
    return alpha;
  }

  public byte getBlue() {
    return blue;
  }

  public byte getGreen() {
    return green;
  }

  public byte getRed() {
    return red;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 53 * hash + this.red;
    hash = 53 * hash + this.green;
    hash = 53 * hash + this.blue;
    hash = 53 * hash + this.alpha;
    return hash;
  }

  @Override
  public String toString() {
    return "Rgba8Pixel{" + "r=" + red + ", g=" + green + ", b=" + blue + ", a=" + alpha + '}';
  }

  private static byte toByte(int value) {
    return (byte) (0xFF & value);
  }

}
