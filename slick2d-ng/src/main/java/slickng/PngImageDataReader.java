package slickng;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Hashtable;
import javax.imageio.ImageIO;

/**
 * {@link ImageDataReader} implementation for reading PNG images.
 */
public class PngImageDataReader implements ImageDataReader {

  private final Color transparent;

  /**
   * The color model including alpha for the GL image.
   */
  private static final ColorModel GL_ALPHA_COLOR_MODEL
          = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
                  new int[]{8, 8, 8, 8},
                  true,
                  false,
                  ComponentColorModel.TRANSLUCENT,
                  DataBuffer.TYPE_BYTE);

  /**
   * Creates a new instance.
   */
  public PngImageDataReader() {
    this(null);
  }

  /**
   * Creates a new instance.
   *
   * @param transparent The color to use for transparency.
   */
  public PngImageDataReader(Color transparent) {
    this.transparent = transparent;
  }

  @Override
  public ImageData read(ImageDataFactory factory, InputStream inputStream) throws IOException {
    return imageToByteBuffer(factory, ImageIO.read(inputStream), transparent);
  }

  private static ImageData imageToByteBuffer(ImageDataFactory factory, BufferedImage image, Color transparent) {
    int width = image.getWidth();
    int height = image.getHeight();

    ImageData imageData = factory.create(PixelFormat.RGBA, width, height);
    int texWidth = imageData.getTextureWidth();
    int texHeight = imageData.getTextureHeight();

    WritableRaster raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 4, null);
    BufferedImage texImage = new BufferedImage(GL_ALPHA_COLOR_MODEL, raster, false, new Hashtable<>());

    // copy the source image into the produced image
    Graphics2D g = (Graphics2D) texImage.getGraphics();

    // need to blank the image for mac compatibility if we're using alpha
    g.setColor(new java.awt.Color(0f, 0f, 0f, 0f));
    g.fillRect(0, 0, texWidth, texHeight);
    g.drawImage(image, 0, 0, null);

    // build a byte buffer from the temporary image 
    // that be used by OpenGL to produce a texture.
    byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();

    if (transparent != null) {
      int[] trans = new int[3];
      trans[0] = (int) (transparent.r * 255);
      trans[1] = (int) (transparent.g * 255);
      trans[2] = (int) (transparent.b * 255);

      for (int i = 0; i < data.length; i += 4) {
        boolean match = true;
        for (int c = 0; c < 3; c++) {
          int value = data[i + c] < 0 ? 256 + data[i + c] : data[i + c];
          if (value != trans[c]) {
            match = false;
          }
        }

        if (match) {
          data[i + 3] = 0;
        }
      }
    }

    ByteBuffer imageBuffer = imageData.getData();
    imageBuffer.put(data, 0, data.length);
    imageBuffer.flip();
    g.dispose();

    return imageData;
  }

}
