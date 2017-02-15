package slickng.gfx;

import java.util.function.Function;
import slickng.SlickException;

import static java.util.Objects.requireNonNull;

public class SurfaceTemplateConverter<S, D> {

  private final PixelConverterBridge<S, D> bridge;
  private final Function<ImageBuffer, ImageBufferReader<S>> readerFactory;
  private final Function<ImageBuffer, ImageBufferWriter<D>> writerFactory;

  public SurfaceTemplateConverter(PixelConverter<S, D> converter, Function<ImageBuffer, ImageBufferReader<S>> readerFactory, Function<ImageBuffer, ImageBufferWriter<D>> writerFactory) {
    this.bridge = new PixelConverterBridge<>(converter);
    this.readerFactory = requireNonNull(readerFactory, "Argument readerFactory must be non-null.");
    this.writerFactory = requireNonNull(writerFactory, "Argument writerFactory must be non-null.");
  }

  public void convert(SurfaceTemplate source, SurfaceTemplate destination) throws SlickException {
    ImageBuffer srcBuffer = source.getBuffer();
    ImageBuffer destBuffer = destination.getBuffer();

    if (srcBuffer.getImageWidth() != destBuffer.getImageWidth()) {
      throw new SlickException(String.format("Source and destination image width differ: %d vs %d.", srcBuffer.getImageWidth(), destBuffer.getImageWidth()));
    }
    if (srcBuffer.getImageHeight() != destBuffer.getImageHeight()) {
      throw new SlickException(String.format("Source and destination image height differ: %d vs %d.", srcBuffer.getImageHeight(), destBuffer.getImageHeight()));
    }

    srcBuffer.rewind();
    destBuffer.rewind();

    int nrOfPixels = srcBuffer.getImageWidth() * srcBuffer.getImageHeight();

    ImageBufferReader<S> reader = readerFactory.apply(srcBuffer);
    ImageBufferWriter<D> writer = writerFactory.apply(destBuffer);
    bridge.transfer(reader, writer, nrOfPixels);
  }
}
