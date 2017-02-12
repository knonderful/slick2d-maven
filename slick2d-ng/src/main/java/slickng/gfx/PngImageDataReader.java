package slickng.gfx;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import slickng.Color;
import slickng.Lease;
import slickng.UnsupportedFormatException;

/**
 * {@link ImageDataReader} implementation for reading PNG images.
 */
public class PngImageDataReader {
  
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
  
  public Lease<ImageDataRgba8> read(ImageDataFactory factory, InputStream inputStream) throws IOException, UnsupportedFormatException {
    return readImageData(factory, ImageIO.read(inputStream), transparent);
  }
  
  private static Lease<ImageDataRgba8> readImageData(ImageDataFactory factory, BufferedImage image, Color transparent) throws IOException, UnsupportedFormatException {
    int width = image.getWidth();
    int height = image.getHeight();
    
    WritableRaster raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, width, height, 4, null);
    BufferedImage texImage = new BufferedImage(GL_ALPHA_COLOR_MODEL, raster, false, new Hashtable<>());

    // copy the source image into the produced image
    Graphics2D g = (Graphics2D) texImage.getGraphics();

    // need to blank the image for mac compatibility if we're using alpha
    g.setColor(new java.awt.Color(0f, 0f, 0f, 0f));
    g.fillRect(0, 0, width, height);
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
    
    Lease<ImageDataRgba8> lease = factory.create(ImageDataRgba8.class, width, height);
    ImageDataRgba8 imageData = lease.borrowSubject();
    try {
      ImageBuffer buffer = imageData.getBuffer();
      buffer.write(new ByteArrayInputStream(data));
    } finally {
      lease.returnSubject(imageData);
    }
    return lease;
  }
}
