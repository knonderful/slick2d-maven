package slickng.gfx;

import slickng.SlickException;

import static java.util.Objects.requireNonNull;

public class PixelConverterBridge<S, D> {

  private final PixelConverter<S, D> converter;

  public PixelConverterBridge(PixelConverter<S, D> converter) {
    this.converter = requireNonNull(converter, "Argument converter must be non-null.");
  }

  /**
   * Transfers pixel data from a supplier to a consumer.
   *
   * @param reader     The pixel reader.
   * @param writer     The pixel writer.
   * @param nrOfPixels The number of pixels to transfer.
   * @throws SlickException If a pixel could not be converted.
   */
  public void transfer(ImageBufferReader<S> reader, ImageBufferWriter<D> writer, int nrOfPixels) throws SlickException {
    for (int i = 0; i < nrOfPixels; i++) {
      writer.write(converter.convert(reader.read()));
    }
  }
}
